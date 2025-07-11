package com.empresa.schoolmanagement.service;

import com.empresa.schoolmanagement.exception.NotFoundException;
import com.empresa.schoolmanagement.model.Course;
import com.empresa.schoolmanagement.model.Student;
import com.empresa.schoolmanagement.repository.CourseRepository;
import com.empresa.schoolmanagement.repository.StudentRepository;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepo;
    private final StudentRepository studentRepo;

    public CourseService(CourseRepository courseRepo, StudentRepository studentRepo) {
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    public Course findById(Long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado, id: " + id));
    }

    public Course save(Course course) {
        return courseRepo.save(course);
    }

    public Course update(Long id, Course courseDetails) {
        Course course = findById(id);
        course.setName(courseDetails.getName());

        // Only update relationships if they're provided
        if (courseDetails.getTeacher() != null) {
            course.setTeacher(courseDetails.getTeacher());
        }

        if (courseDetails.getClassroom() != null) {
            course.setClassroom(courseDetails.getClassroom());
        }

        return courseRepo.save(course);
    }

    public void delete(Long id) {
        Course course = findById(id);
        courseRepo.delete(course);
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
