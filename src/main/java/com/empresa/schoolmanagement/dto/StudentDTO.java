package com.empresa.schoolmanagement.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private AddressDTO address;
}

