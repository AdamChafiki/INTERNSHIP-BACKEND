package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "internships")
//@JsonIdentityInfo(
//        generator = Obje
//)
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String presence;

    @Column(nullable = false)
    private String location;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @JsonBackReference
    @OneToMany(mappedBy = "internship", cascade = CascadeType.ALL)
    private List<InternshipApplication> applications;


    @Column(nullable = false, updatable = false) // `createdAt` should not be updated after creation
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;



    // Lifecycle Callbacks for Automatic Timestamps
    @PrePersist
    protected void onCreate() {
        LocalDate now = LocalDate.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
