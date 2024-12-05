package com.example.demo.repository;


import com.example.demo.model.Internship;
import com.example.demo.model.InternshipApplication;
import com.example.demo.model.InternshipSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface InternshipApplicationRepository extends JpaRepository<InternshipApplication, Long> {

     List<InternshipApplication> findByInternshipSeekerId(Long internshipSeekerId);
    @Transactional
    int  deleteByInternshipSeekerId(Long internshipSeekerId);

    boolean existsByInternshipAndInternshipSeeker(Internship internship, InternshipSeeker internshipSeeker);
}
