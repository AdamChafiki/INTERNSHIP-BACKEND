package com.example.demo.controller;

import com.example.demo.dto.InternshipDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Internship;
import com.example.demo.responses.InternshipResponse;
import com.example.demo.service.EmailService;
import com.example.demo.service.InternshipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;


@RequestMapping("/internship")
@RestController

public class InternshipController {
    private final InternshipService internshipService;
    private final EmailService emailService;
    public InternshipController(InternshipService internshipService, EmailService emailService) {
        this.internshipService = internshipService;
        this.emailService = emailService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<InternshipResponse>> getAllInternships() {
        return ResponseEntity.ok(internshipService.getAllInternships());
    }

    @GetMapping
    public ResponseEntity<List<InternshipResponse>> searchInternships(@RequestParam(value = "q", required = false) String query) {
        return ResponseEntity.ok(internshipService.searchInternshipsByTitle(query));
    }

    @PostMapping("/add")
    public ResponseEntity<Internship> createCompany(@RequestBody InternshipDto internshipDto ) {
        return ResponseEntity.status(201).body(internshipService.saveInternship(internshipDto));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Internship>> getInternshipByCompanyId(@PathVariable Long companyId) {
            return  ResponseEntity.status(200).body(internshipService.getByCompanyId(companyId));
    }

    @GetMapping("/{internshipId}")
    public ResponseEntity<InternshipResponse> getInternshipById(@PathVariable Long internshipId) {
        return  ResponseEntity.status(200).body(internshipService.getInternshipById(internshipId));
    }



    @PutMapping("update/{id}")
    public ResponseEntity<Internship> updateInternship(@PathVariable Long id, @RequestBody InternshipDto internshipDto ) {
        try {
            Internship updatedCompany = internshipService.updateInternship(id, internshipDto);
            return ResponseEntity.ok(updatedCompany);
        } catch (EntityNotFoundException e) {
            // Return a 404 if the company is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/location")
    public ResponseEntity<List<InternshipResponse>> getInternships(
            @RequestParam(required = false) String location) {
        return ResponseEntity.ok(internshipService.getInternshipByLocation(location));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInternship(@PathVariable Long id) {
        internshipService.deleteInternship(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PostMapping("/send-internship-request")
    public ResponseEntity<String> sendInternshipRequest(
            @RequestParam("companyEmail") String companyEmail,
            @RequestParam("seekerName") String seekerName,
            @RequestParam("seekerEmail") String seekerEmail,
            @RequestParam("seekerMessage") String seekerMessage,
            @RequestParam("cv") MultipartFile cv) {
        try {
            // Save CV locally (optional) or directly use it
            File cvFile = new File(System.getProperty("java.io.tmpdir") + "/" + cv.getOriginalFilename());
            cv.transferTo(cvFile);

            // Send email with attachment
            emailService.sendInternshipRequestEmail(companyEmail, seekerName, seekerEmail, seekerMessage, cvFile);

            // Delete the temporary file after sending the email (optional)
            cvFile.delete();

            return ResponseEntity.ok("Email sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email: " + e.getMessage());
        }
    }







}
