package com.empresa.schoolmanagement.mapper;


import com.empresa.schoolmanagement.dto.CourseDTO;
import com.empresa.schoolmanagement.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO toDTO(Course course);
    Course toEntity(CourseDTO dto);
}
