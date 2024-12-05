package com.example.demo.repository;

import com.example.demo.model.InternshipSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipSeekerRepository extends JpaRepository<InternshipSeeker, Long> {

    InternshipSeeker findByUserId(Long id);
}
