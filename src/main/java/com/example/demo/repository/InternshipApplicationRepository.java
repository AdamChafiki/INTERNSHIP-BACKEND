package com.example.demo.repository;


import com.example.demo.model.Internship;
import com.example.demo.model.InternshipApplication;
import com.example.demo.model.InternshipSeeker;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface InternshipApplicationRepository extends JpaRepository<InternshipApplication, Long> {


    @Query("SELECT ia FROM InternshipApplication ia " +
            "JOIN ia.internship i " +
            "JOIN i.company c " +
            "WHERE c.id = :companyId")
    List<InternshipApplication> findByCompanyId(@Param("companyId") Long companyId);


    List<InternshipApplication> findByInternshipSeekerId(Long internshipSeekerId);

    @Transactional
    void deleteById(@NotNull Long internshipSeekerId);

    boolean existsByInternshipAndInternshipSeeker(Internship internship, InternshipSeeker internshipSeeker);


}
