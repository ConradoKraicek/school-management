package com.empresa.schoolmanagement.controller;

import com.empresa.schoolmanagement.dto.ClassroomDTO;
import com.empresa.schoolmanagement.mapper.ClassroomMapper;
import com.empresa.schoolmanagement.model.Classroom;
import com.empresa.schoolmanagement.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/classrooms")
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassroomService classroomService;
    private final ClassroomMapper classroomMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ClassroomDTO>> getAllClassrooms() {
        List<Classroom> classrooms = classroomService.findAll();
        List<ClassroomDTO> classroomDTOs = classrooms.stream()
                .map(classroomMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(classroomDTOs);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClassroomDTO> getClassroomById(@PathVariable Long id) {
        Classroom classroom = classroomService.findById(id);
        ClassroomDTO classroomDTO = classroomMapper.toDTO(classroom);
        return ResponseEntity.ok(classroomDTO);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClassroomDTO> createClassroom(@RequestBody ClassroomDTO classroomDTO) {
        Classroom classroom = classroomMapper.toEntity(classroomDTO);
        Classroom savedClassroom = classroomService.save(classroom);
        ClassroomDTO savedClassroomDTO = classroomMapper.toDTO(savedClassroom);
        return new ResponseEntity<>(savedClassroomDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClassroomDTO> updateClassroom(@PathVariable Long id, @RequestBody ClassroomDTO classroomDTO) {
        Classroom classroom = classroomMapper.toEntity(classroomDTO);
        Classroom updatedClassroom = classroomService.update(id, classroom);
        ClassroomDTO updatedClassroomDTO = classroomMapper.toDTO(updatedClassroom);
        return ResponseEntity.ok(updatedClassroomDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long id) {
        classroomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}