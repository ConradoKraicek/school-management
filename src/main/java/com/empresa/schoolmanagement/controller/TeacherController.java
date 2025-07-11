package com.empresa.schoolmanagement.controller;

import com.empresa.schoolmanagement.dto.TeacherDTO;
import com.empresa.schoolmanagement.mapper.TeacherMapper;
import com.empresa.schoolmanagement.model.Teacher;
import com.empresa.schoolmanagement.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<Teacher> teachers = teacherService.findAll();
        List<TeacherDTO> teacherDTOs = teachers.stream()
                .map(teacherMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(teacherDTOs);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.findById(id);
        TeacherDTO teacherDTO = teacherMapper.toDTO(teacher);
        return ResponseEntity.ok(teacherDTO);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        Teacher teacher = teacherMapper.toEntity(teacherDTO);
        Teacher savedTeacher = teacherService.save(teacher);
        TeacherDTO savedTeacherDTO = teacherMapper.toDTO(savedTeacher);
        return new ResponseEntity<>(savedTeacherDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO) {
        Teacher teacher = teacherMapper.toEntity(teacherDTO);
        Teacher updatedTeacher = teacherService.update(id, teacher);
        TeacherDTO updatedTeacherDTO = teacherMapper.toDTO(updatedTeacher);
        return ResponseEntity.ok(updatedTeacherDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}