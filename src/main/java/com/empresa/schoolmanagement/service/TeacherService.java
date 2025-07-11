package com.empresa.schoolmanagement.service;

import com.empresa.schoolmanagement.exception.NotFoundException;
import com.empresa.schoolmanagement.model.Teacher;
import com.empresa.schoolmanagement.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Teacher findById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Professor não encontrado, id: " + id));
    }

    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher update(Long id, Teacher teacherDetails) {
        Teacher teacher = findById(id);
        teacher.setName(teacherDetails.getName());
        teacher.setEmail(teacherDetails.getEmail());
        
        // Only update address if it's provided
        if (teacherDetails.getAddress() != null) {
            teacher.setAddress(teacherDetails.getAddress());
        }
        
        return teacherRepository.save(teacher);
    }

    public void delete(Long id) {
        Teacher teacher = findById(id);
        teacherRepository.delete(teacher);
    }
}