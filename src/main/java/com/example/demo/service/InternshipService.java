package com.example.demo.service;

import com.example.demo.dto.InternshipDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Company;
import com.example.demo.model.Internship;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.InternshipRepository;
import com.example.demo.responses.InternshipResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InternshipService {

    private final InternshipRepository internshipRepository;
    private final CompanyRepository companyRepository;

    // Constructor injection
    public InternshipService(InternshipRepository internshipRepository, CompanyRepository companyRepository) {
        this.internshipRepository = internshipRepository;
        this.companyRepository = companyRepository;
    }

    // Save Internship
    public Internship saveInternship(InternshipDto input) {
        Company company = companyRepository.findById(input.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));


        // Create an Internship object
        Internship internship = new Internship(
                null,
                input.getTitle(),
                input.getDescription(),
                input.getDuration(),
                input.getType(),
                input.getPresence(),
                input.getLocation(),
                company,                     // Set the fetched company
                null,                        // createdAt (auto-handled)
                null,
                null
        );

        // Save and return the internship
        return internshipRepository.save(internship);
    }

    public List<Internship> getByCompanyId(Long companyId) {
        return internshipRepository.findInternshipByCompanyId(companyId);
    }

    public Internship updateInternship(Long id, InternshipDto internshipDto) {
        Optional<Internship> existingInternshipOpt = internshipRepository.findById(id);

        if (existingInternshipOpt.isPresent()) {
            Internship existingInternship = existingInternshipOpt.get();

            if (internshipDto.getTitle() != null) existingInternship.setTitle(internshipDto.getTitle());
            if (internshipDto.getDescription() != null) existingInternship.setDescription(internshipDto.getDescription());
            if (internshipDto.getPresence() != null) existingInternship.setLocation(internshipDto.getPresence());
            if (internshipDto.getLocation() != null) existingInternship.setLocation(internshipDto.getLocation());
            if (internshipDto.getType() != null) existingInternship.setLocation(internshipDto.getType());
            if (internshipDto.getDuration() != -1) existingInternship.setDuration(internshipDto.getDuration());

            return internshipRepository.save(existingInternship);
        }

        throw new EntityNotFoundException("Internship with id " + id + " not found.");
    }


    public void deleteInternship(Long id) {
        internshipRepository.deleteById(id);
    }

    public List<InternshipResponse> getAllInternships() {
        List<Internship> internships = internshipRepository.findAll();
        return internships.stream()
                .map(internship -> new InternshipResponse(
                        internship.getId(),
                        internship.getTitle(),
                        internship.getDescription(),
                        internship.getDuration(),
                        internship.getType(),
                        internship.getPresence(),
                        internship.getLocation(),
                        internship.getCreatedAt(),
                        internship.getCompany().getName() // Assuming `Company` has a `getName()` method
                ))
                .collect(Collectors.toList());
    }

    public List<Internship> searchInternshipsByTitle(String query) {
        return internshipRepository.findByTitleContainingIgnoreCase(query);
    }

//    here should  add company information
public InternshipResponse getInternshipById(Long id) {
    // Find the internship by its ID
    Internship internship = internshipRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Internship not found"));

    // Build the response including company information
    return new InternshipResponse(
            internship.getId(),
            internship.getTitle(),
            internship.getDescription(),
            internship.getDuration(),
            internship.getType(),
            internship.getPresence(),
            internship.getLocation(),
            internship.getCreatedAt(),
            internship.getCompany().getName()
    );
}

}
