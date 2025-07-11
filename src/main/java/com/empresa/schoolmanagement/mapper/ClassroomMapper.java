package com.empresa.schoolmanagement.mapper;


import com.empresa.schoolmanagement.dto.ClassroomDTO;
import com.empresa.schoolmanagement.model.Classroom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {
    ClassroomDTO toDTO(Classroom classroom);
    Classroom toEntity(ClassroomDTO dto);
}
