package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Data
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "internship_seekers")
public class InternshipSeeker {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Getter
    @Column(nullable = false)
    private String schoolName;
    @Column(nullable = false)
    private String level;
    @Column(nullable = false)
    private String specialty;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private int duration;
    private String resumeUrl;

    @JsonBackReference
    @OneToMany(mappedBy = "internshipSeeker", cascade = CascadeType.ALL)
    private List<InternshipApplication> applications;



}
