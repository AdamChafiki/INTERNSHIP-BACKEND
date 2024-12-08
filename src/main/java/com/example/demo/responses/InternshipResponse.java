package com.example.demo.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class InternshipResponse {
    private Long id;
    private String title;
    private String description;
    private int duration;
    private String type;
    private String presence;
    private String location;
    private LocalDate createdAt;
    private String companyName;
    private Long companyId;




}

