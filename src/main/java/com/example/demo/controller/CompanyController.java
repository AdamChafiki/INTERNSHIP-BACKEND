package com.example.demo.controller;


import com.example.demo.dto.CompanyDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Company;
import com.example.demo.model.User;
import com.example.demo.responses.InternshipResponse;
import com.example.demo.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/company")
@RestController

public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @PostMapping("/add")
    public ResponseEntity<Company> createCompany(@RequestBody CompanyDto companyDto) {
        return ResponseEntity.status(201).body(companyService.saveCompany(companyDto));
    }

    @GetMapping("/")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Company> getCompanyByUserId(@PathVariable Long userId) {
        Optional<Company> company = companyService.getCompanyByUserId(userId);

        // Return 404 if no company found for that user
        return company.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{companyId}/latest-internships")
    public ResponseEntity<List<InternshipResponse>> getLatestInternshipsByCompanyId(@PathVariable Long companyId) {
        List<InternshipResponse> internships = companyService.getLatestInternshipsByCompanyId(companyId);
        return ResponseEntity.ok(internships);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
        try {
            Company updatedCompany = companyService.updateCompany(id, companyDto);
            return ResponseEntity.ok(updatedCompany);
        } catch (EntityNotFoundException e) {
            // Return a 404 if the company is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
