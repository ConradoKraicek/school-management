package com.empresa.schoolmanagement.service;

import com.empresa.schoolmanagement.exception.NotFoundException;
import com.empresa.schoolmanagement.model.Classroom;
import com.empresa.schoolmanagement.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    public Classroom findById(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sala de aula não encontrada, id: " + id));
    }

    public Classroom save(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public Classroom update(Long id, Classroom classroomDetails) {
        Classroom classroom = findById(id);
        classroom.setName(classroomDetails.getName());
        classroom.setLocation(classroomDetails.getLocation());
        return classroomRepository.save(classroom);
    }

    public void delete(Long id) {
        Classroom classroom = findById(id);
        classroomRepository.delete(classroom);
    }
}