package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String sector;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private String urlWebsite;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "company")
    private List<Internship> internships;


    public Company() {}

    // All-args constructor (for manual creation)
    public Company(String name, String address, String sector, String type, String description, String urlWebsite, User user) {
        this.name = name;
        this.address = address;
        this.sector = sector;
        this.type = type;
        this.description = description;
        this.urlWebsite = urlWebsite;
        this.user = user;
    }

//new
    public Company(Long companyId) {
    }
}
