package com.empresa.schoolmanagement.repository;

import com.empresa.schoolmanagement.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
