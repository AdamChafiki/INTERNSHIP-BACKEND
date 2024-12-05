package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private String name;

    private String address;

    private String sector;

    private String type;

    private String description;

    private String urlWebsite;

    private Long userId;
}
