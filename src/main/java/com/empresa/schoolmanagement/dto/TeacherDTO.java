package com.empresa.schoolmanagement.dto;



import lombok.Data;

@Data
public class TeacherDTO {
    private Long id;
    private String name;
    private String email;
    private AddressDTO address;
}

