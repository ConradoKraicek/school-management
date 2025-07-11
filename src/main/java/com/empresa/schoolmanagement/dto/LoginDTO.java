package com.empresa.schoolmanagement.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
    private String role; // Optional, can be used to specify the user role if needed
}
