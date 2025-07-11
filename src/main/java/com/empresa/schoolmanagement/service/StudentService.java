package com.empresa.schoolmanagement.service;

import com.empresa.schoolmanagement.exception.NotFoundException;
import com.empresa.schoolmanagement.model.Student;
import com.empresa.schoolmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estudante não encontrado, id: " + id));
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Long id, Student studentDetails) {
        Student student = findById(id);
        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        
        // Only update address if it's provided
        if (studentDetails.getAddress() != null) {
            student.setAddress(studentDetails.getAddress());
        }
        
        return studentRepository.save(student);
    }

    public void delete(Long id) {
        Student student = findById(id);
        studentRepository.delete(student);
    }
}