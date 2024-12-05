package com.example.demo.repository;

import com.example.demo.model.Internship;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InternshipRepository  extends JpaRepository<Internship, Long> {
    @Query("SELECT i FROM Internship i WHERE i.company.id = :companyId ORDER BY i.createdAt DESC")
    List<Internship> findLatestInternshipsByCompanyId(Long companyId, PageRequest pageable);
    List<Internship>  findInternshipByCompanyId(Long userId);
    List<Internship> findByTitleContainingIgnoreCase(String title);
}
