package com.empresa.schoolmanagement.mapper;


import com.empresa.schoolmanagement.dto.TeacherDTO;
import com.empresa.schoolmanagement.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherDTO toDTO(Teacher teacher);
    Teacher toEntity(TeacherDTO dto);
}
