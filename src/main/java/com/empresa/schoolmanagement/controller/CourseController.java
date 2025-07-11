package com.empresa.schoolmanagement.controller;

import com.empresa.schoolmanagement.dto.CourseDTO;
import com.empresa.schoolmanagement.mapper.CourseMapper;
import com.empresa.schoolmanagement.model.Course;
import com.empresa.schoolmanagement.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping("/{courseId}/enroll/{studentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseDTO> enrollStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        Course course = courseService.enrollStudent(courseId, studentId);
        CourseDTO dto = courseMapper.toDTO(course);
        return ResponseEntity.ok(dto);
    }
}

