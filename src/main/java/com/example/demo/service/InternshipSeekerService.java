package com.example.demo.service;

import com.example.demo.dto.CompanyDto;
import com.example.demo.dto.InternshipSeekerDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Company;
import com.example.demo.model.InternshipSeeker;
import com.example.demo.model.User;
import com.example.demo.repository.InternshipSeekerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class InternshipSeekerService {

    private final InternshipSeekerRepository internshipSeekerRepository;
    private final UserService userService;

    // Constructor injection
    public InternshipSeekerService(InternshipSeekerRepository internshipSeekerRepository, UserService userService) {
        this.internshipSeekerRepository = internshipSeekerRepository;
        this.userService = userService;
    }

    public InternshipSeeker getInternshipSeeker(Long id) {
        return internshipSeekerRepository.findByUserId(id);
    }


    // Save InternshipSeeker
    public InternshipSeekerDto saveInternshipSeeker(InternshipSeekerDto internshipSeekerDto) {
        User user = userService.findById(internshipSeekerDto.getUserId());
        InternshipSeeker internshipSeeker = new InternshipSeeker();
        internshipSeeker.setId(internshipSeekerDto.getId());
        internshipSeeker.setSchoolName(internshipSeekerDto.getSchoolName());
        internshipSeeker.setLevel(internshipSeekerDto.getLevel());
        internshipSeeker.setSpecialty(internshipSeekerDto.getSpecialty());
        internshipSeeker.setDescription(internshipSeekerDto.getDescription());
        internshipSeeker.setDuration(internshipSeekerDto.getDuration());
        internshipSeeker.setStartDate(internshipSeekerDto.getStartDate());
        internshipSeeker.setResumeUrl(null);
        internshipSeeker.setUser(user);

        // Save the Entity
        InternshipSeeker savedEntity = internshipSeekerRepository.save(internshipSeeker);

        // Map Entity back to DTO
        return new InternshipSeekerDto(
                savedEntity.getId(),
                savedEntity.getSchoolName(),
                savedEntity.getLevel(),
                savedEntity.getSpecialty(),
                savedEntity.getDescription(),
                savedEntity.getDuration(),
                savedEntity.getStartDate(),
                savedEntity.getResumeUrl(),
                user.getId()
        );
    }

    // Save or Update InternshipSeeker
    public InternshipSeekerDto saveOrUpdateInternshipSeeker(InternshipSeekerDto internshipSeekerDto) {
        User user = userService.findById(internshipSeekerDto.getUserId());

        // Check if an InternshipSeeker already exists for the given user
        InternshipSeeker internshipSeeker = internshipSeekerRepository.findByUserId(internshipSeekerDto.getUserId());
        if (internshipSeeker == null) {
            internshipSeeker = new InternshipSeeker(); // Create a new one if it doesn't exist
        }

        // Update InternshipSeeker fields
        internshipSeeker.setSchoolName(internshipSeekerDto.getSchoolName());
        internshipSeeker.setLevel(internshipSeekerDto.getLevel());
        internshipSeeker.setSpecialty(internshipSeekerDto.getSpecialty());
        internshipSeeker.setDescription(internshipSeekerDto.getDescription());
        internshipSeeker.setDuration(internshipSeekerDto.getDuration());
        internshipSeeker.setStartDate(internshipSeekerDto.getStartDate());
        internshipSeeker.setResumeUrl(internshipSeekerDto.getResume()); // Set resume URL
        internshipSeeker.setUser(user);

        // Save the InternshipSeeker
        InternshipSeeker savedEntity = internshipSeekerRepository.save(internshipSeeker);

        // Map back to DTO and return
        return new InternshipSeekerDto(
                savedEntity.getId(),
                savedEntity.getSchoolName(),
                savedEntity.getLevel(),
                savedEntity.getSpecialty(),
                savedEntity.getDescription(),
                savedEntity.getDuration(),
                savedEntity.getStartDate(),
                savedEntity.getResumeUrl(),
                user.getId()
        );
    }

    public InternshipSeeker updateResume(Long userId, String resumePath) {
        InternshipSeeker seeker = internshipSeekerRepository.findById(userId).orElse(null);
        if (seeker != null) {
            seeker.setResumeUrl(resumePath);
            return internshipSeekerRepository.save(seeker);
        }
        return null;
    }



}