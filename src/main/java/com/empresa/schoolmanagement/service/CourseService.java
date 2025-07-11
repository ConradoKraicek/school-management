package com.empresa.schoolmanagement.service;

import com.empresa.schoolmanagement.exception.NotFoundException;
import com.empresa.schoolmanagement.model.Course;
import com.empresa.schoolmanagement.model.Student;
import com.empresa.schoolmanagement.repository.CourseRepository;
import com.empresa.schoolmanagement.repository.StudentRepository;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepo;
    private final StudentRepository studentRepo;

    public CourseService(CourseRepository courseRepo, StudentRepository studentRepo) {
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

    @Transactional
    public Course enrollStudent(Long courseId, Long studentId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado, id: " + courseId));
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado, id: " + studentId));
        course.getStudents().add(student);
        return courseRepo.save(course);
    }
}

