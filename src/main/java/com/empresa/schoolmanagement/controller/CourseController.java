package com.empresa.schoolmanagement.controller;

import com.empresa.schoolmanagement.dto.CourseDTO;
import com.empresa.schoolmanagement.mapper.CourseMapper;
import com.empresa.schoolmanagement.model.Course;
import com.empresa.schoolmanagement.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<Course> courses = courseService.findAll();
        List<CourseDTO> courseDTOs = courses.stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(courseDTOs);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        Course course = courseService.findById(id);
        CourseDTO courseDTO = courseMapper.toDTO(course);
        return ResponseEntity.ok(courseDTO);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        Course savedCourse = courseService.save(course);
        CourseDTO savedCourseDTO = courseMapper.toDTO(savedCourse);
        return new ResponseEntity<>(savedCourseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        Course updatedCourse = courseService.update(id, course);
        CourseDTO updatedCourseDTO = courseMapper.toDTO(updatedCourse);
        return ResponseEntity.ok(updatedCourseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{courseId}/enroll/{studentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseDTO> enrollStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        Course course = courseService.enrollStudent(courseId, studentId);
        CourseDTO dto = courseMapper.toDTO(course);
        return ResponseEntity.ok(dto);
    }
}
