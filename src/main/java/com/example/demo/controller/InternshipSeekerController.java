package com.example.demo.controller;

import com.example.demo.dto.InternshipSeekerDto;
import com.example.demo.model.InternshipSeeker;
import com.example.demo.service.InternshipSeekerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/internshipSeeker")
@RestController

public class InternshipSeekerController {
    private final InternshipSeekerService internshipSeekerService;

    public InternshipSeekerController(InternshipSeekerService internshipSeekerService) {
        this.internshipSeekerService = internshipSeekerService;
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<InternshipSeeker> getInternshipSeeker(@PathVariable Long userId) {
        return ResponseEntity.ok(internshipSeekerService.getInternshipSeeker(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<InternshipSeekerDto> createCompany(@RequestBody InternshipSeekerDto internshipSeekerDto ) {
        return ResponseEntity.status(201).body(internshipSeekerService.saveInternshipSeeker(internshipSeekerDto));
    }

    @PutMapping("/update")
    public ResponseEntity<InternshipSeekerDto> saveOrUpdateInternshipSeeker(@RequestBody InternshipSeekerDto internshipSeekerDto) {
        InternshipSeekerDto savedDto = internshipSeekerService.saveOrUpdateInternshipSeeker(internshipSeekerDto);
        return ResponseEntity.ok(savedDto);
    }

}
