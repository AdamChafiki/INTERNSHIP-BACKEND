package com.example.demo.responses;


import java.time.LocalDate;

public class InternshipResponse {
    private Long id;
    private String title;
    private String description;
    private int duration;
    private String type;
    private String presence;



    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    private String location;
    private LocalDate createdAt;
    private String companyName;

    public InternshipResponse(Long id, String title, String description, int duration, String type, String presence, String location,LocalDate createdAt ,String companyName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.type = type;
        this.presence = presence;
        this.location = location;
        this.createdAt = createdAt;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

