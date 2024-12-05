package com.example.demo.controller;

import com.example.demo.dto.InternshipApplicationDTO;
import com.example.demo.model.InternshipApplication;
import com.example.demo.service.InternshipApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/job-application")
public class InternshipApplicationController {

    private final InternshipApplicationService internshipApplicationService;

    public InternshipApplicationController(InternshipApplicationService internshipApplicationService) {
        this.internshipApplicationService = internshipApplicationService;
    }


    @GetMapping("")
    public List<InternshipApplication> getInternshipApplications() {
        return internshipApplicationService.findAll();
    }

    @GetMapping("/internshipSeeker/{internshipSeekerId}")
    public List<InternshipApplication> getInternshipApplications(@PathVariable Long internshipSeekerId) {
        return internshipApplicationService.findByInternshipSeekerId(internshipSeekerId);
    }

    @PostMapping("")
    public InternshipApplication createApplication(@RequestBody InternshipApplicationDTO dto) {
        return internshipApplicationService.createApplication(dto);
    }

    @DeleteMapping("/internshipSeeker/{internshipSeekerId}")
    public int deleteByInternshipSeekerId(@PathVariable Long internshipSeekerId) {
        return internshipApplicationService.deleteByInternshipSeekerId(internshipSeekerId);
    }


}
