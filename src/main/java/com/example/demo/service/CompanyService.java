package com.example.demo.service;


import com.example.demo.dto.CompanyDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Company;
import com.example.demo.model.Internship;
import com.example.demo.model.User;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.InternshipRepository;
import com.example.demo.responses.InternshipResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final UserService userService;
    private final InternshipRepository internshipRepository;

    public CompanyService(CompanyRepository companyRepository, InternshipRepository internshipRepository, UserService userService) {
        this.companyRepository = companyRepository;
        this.internshipRepository = internshipRepository;
        this.userService = userService;
    }

    public Company getCompanyById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            return company.get();
        }
        throw new EntityNotFoundException("Company not found");
    }

    public Company saveCompany(CompanyDto input) {


        User user = userService.findById(input.getUserId());
        Company company = new Company(
                input.getName(),
                input.getAddress(),
                input.getSector(),
                input.getType(),
                input.getDescription(),
                input.getUrlWebsite(),
                user
        );
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyByUserId(Long userId) {
        return companyRepository.findByUserId(userId);
    }

    public Company updateCompany(Long id, CompanyDto companyDto) {
        Optional<Company> existingCompanyOpt = companyRepository.findById(id);

        if (existingCompanyOpt.isPresent()) {
            Company existingCompany = existingCompanyOpt.get();

            // Update the fields that are provided in the DTO
            if (companyDto.getName() != null) existingCompany.setName(companyDto.getName());
            if (companyDto.getAddress() != null) existingCompany.setAddress(companyDto.getAddress());
            if (companyDto.getSector() != null) existingCompany.setSector(companyDto.getSector());
            if (companyDto.getType() != null) existingCompany.setType(companyDto.getType());
            if (companyDto.getDescription() != null) existingCompany.setDescription(companyDto.getDescription());
            if (companyDto.getUrlWebsite() != null) existingCompany.setUrlWebsite(companyDto.getUrlWebsite());

            // Save the updated company
            return companyRepository.save(existingCompany);
        }

        // If the company is not found, throw an exception or return a custom response
        throw new EntityNotFoundException("Company with id " + id + " not found.");
    }

    //
    public List<InternshipResponse> getLatestInternshipsByCompanyId(Long companyId) {
        // Fetch the top 3 internships posted by the company, ordered by createdAt DESC
        return internshipRepository
                .findLatestInternshipsByCompanyId(companyId, PageRequest.of(0, 3))
                .stream()
                .map(this::mapToInternshipResponse)
                .toList();
    }

    private InternshipResponse mapToInternshipResponse(Internship internship) {
        return new InternshipResponse(internship.getId(),
                internship.getTitle(),
                internship.getDescription(),
                internship.getDuration(),
                internship.getType(),
                internship.getPresence(),
                internship.getLocation(),
                internship.getCreatedAt(),
                internship.getCompany().getName(), internship.getCompany().getId()
        );

    }

}
