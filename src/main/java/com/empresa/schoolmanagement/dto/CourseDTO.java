package com.empresa.schoolmanagement.dto;

import lombok.Data;
import java.util.Set;

@Data
public class CourseDTO {
    private Long id;
    private String name;
    private TeacherDTO teacher;
    private ClassroomDTO classroom;
    // Pode ser apenas id dos alunos para evitar ciclos infinitos, ou lista resumida:
    private Set<StudentSimpleDTO> students;
}
