package com.empresa.schoolmanagement.mapper;

import com.empresa.schoolmanagement.dto.StudentDTO;
import com.empresa.schoolmanagement.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDTO toDTO(Student student);
    Student toEntity(StudentDTO dto);
}
