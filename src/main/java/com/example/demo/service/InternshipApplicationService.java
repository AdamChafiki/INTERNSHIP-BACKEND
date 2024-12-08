package com.example.demo.service;


import com.example.demo.dto.InternshipApplicationDTO;
import com.example.demo.model.Internship;
import com.example.demo.model.InternshipApplication;
import com.example.demo.model.InternshipSeeker;
import com.example.demo.repository.InternshipApplicationRepository;
import com.example.demo.repository.InternshipRepository;
import com.example.demo.repository.InternshipSeekerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InternshipApplicationService {

    private final InternshipApplicationRepository internshipApplicationRepository;
    private final InternshipRepository internshipRepository;
    private final InternshipSeekerRepository internshipSeekerRepository;
    public InternshipApplicationService(InternshipApplicationRepository internshipApplicationRepository, InternshipRepository internshipRepository, InternshipSeekerRepository internshipSeekerRepository) {
        this.internshipApplicationRepository = internshipApplicationRepository;
        this.internshipRepository = internshipRepository;

        this.internshipSeekerRepository = internshipSeekerRepository;
    }


    public List<InternshipApplication> findAll() {
        return internshipApplicationRepository.findAll();
    }

    public List<InternshipApplication> findByInternshipSeekerId(Long internshipSeekerId) {
        return internshipApplicationRepository.findByInternshipSeekerId(internshipSeekerId);
    }

    public InternshipApplication createApplication(InternshipApplicationDTO dto) {
        // Find the Internship by ID
        Internship internship = internshipRepository.findById(dto.getInternshipId())
                .orElseThrow(() -> new RuntimeException("Internship not found"));

        // Find the InternshipSeeker by ID
        InternshipSeeker internshipSeeker = internshipSeekerRepository.findById(dto.getInternshipSeekerId())
                .orElseThrow(() -> new RuntimeException("Internship Seeker not found"));

        // Check if the InternshipApplication already exists
        boolean alreadyApplied = internshipApplicationRepository.existsByInternshipAndInternshipSeeker(internship, internshipSeeker);
        if (alreadyApplied) {
            throw new RuntimeException("You have already applied for this internship!");
        }

        // Create and Save the InternshipApplication
        InternshipApplication application = new InternshipApplication();
        application.setInternship(internship);
        application.setInternshipSeeker(internshipSeeker);

        return internshipApplicationRepository.save(application);
    }


    public List<InternshipApplication> findApplicationsByCompanyId(Long companyId) {
        return internshipApplicationRepository.findByCompanyId(companyId);
    }


    @Transactional
    public int deleteByInternshipSeekerId(Long applicationId) {
        internshipApplicationRepository.deleteById(applicationId);
        return 0;
    }


}
