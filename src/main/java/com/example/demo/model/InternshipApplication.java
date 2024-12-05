package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_applications")
public class InternshipApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "internship_id", nullable = false)
    @JsonManagedReference
    private Internship internship;

    @ManyToOne
    @JoinColumn(name = "internshipseeker_id", nullable = false)
    @JsonManagedReference
    private InternshipSeeker internshipSeeker;

    @Column(nullable = false)
    private LocalDate appliedAt;

    @PrePersist
    protected void onApply() {
        this.appliedAt = LocalDate.from(LocalDateTime.now());
    }
}
