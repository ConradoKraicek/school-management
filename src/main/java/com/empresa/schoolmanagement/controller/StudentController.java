package com.empresa.schoolmanagement.controller;

import com.empresa.schoolmanagement.dto.StudentDTO;
import com.empresa.schoolmanagement.mapper.StudentMapper;
import com.empresa.schoolmanagement.model.Student;
import com.empresa.schoolmanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<Student> students = studentService.findAll();
        List<StudentDTO> studentDTOs = students.stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentDTOs);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        Student student = studentService.findById(id);
        StudentDTO studentDTO = studentMapper.toDTO(student);
        return ResponseEntity.ok(studentDTO);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        Student savedStudent = studentService.save(student);
        StudentDTO savedStudentDTO = studentMapper.toDTO(savedStudent);
        return new ResponseEntity<>(savedStudentDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        Student updatedStudent = studentService.update(id, student);
        StudentDTO updatedStudentDTO = studentMapper.toDTO(updatedStudent);
        return ResponseEntity.ok(updatedStudentDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}