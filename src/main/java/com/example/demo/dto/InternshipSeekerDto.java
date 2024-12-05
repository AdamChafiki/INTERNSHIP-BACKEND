package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternshipSeekerDto {

    private Long id;
    private String schoolName;
    private String level;
    private String specialty;
    private String description;
    private int duration;
    private LocalDate startDate;
    private String resume;
    private Long userId;

}
