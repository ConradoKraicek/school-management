package com.empresa.schoolmanagement.mapper;

import com.empresa.schoolmanagement.dto.StudentDTO;
import com.empresa.schoolmanagement.model.Student;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO toDTO(Student student);
    Student toEntity(StudentDTO dto);
}
