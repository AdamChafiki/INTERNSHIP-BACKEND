package com.example.demo.controller;

import com.example.demo.dto.InternshipDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Internship;
import com.example.demo.responses.InternshipResponse;
import com.example.demo.service.InternshipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/internship")
@RestController

public class InternshipController {
    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<InternshipResponse>> getAllInternships() {
        return ResponseEntity.ok(internshipService.getAllInternships());
    }

    @GetMapping
    public ResponseEntity<List<Internship>> searchInternships(@RequestParam(value = "q", required = false) String query) {
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInternship(@PathVariable Long id) {
        internshipService.deleteInternship(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
