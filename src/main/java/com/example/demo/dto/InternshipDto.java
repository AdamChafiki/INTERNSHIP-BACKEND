package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternshipDto {

    private Long id;
    private String title;
    private String description;
    private int duration;        // Duration of the internship in weeks or months
    private String type;         // Internship type (e.g., "Full-time", "Part-time")
    private String presence;        // Presence type (e.g., 0 = Remote, 1 = On-site)
    private String location;     // Location of the internship
    private Long companyId;  // Company name for simplicity in the DTO
}
