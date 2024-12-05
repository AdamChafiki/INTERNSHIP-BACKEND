package com.example.demo.dto;


import com.example.demo.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Role role;
}

