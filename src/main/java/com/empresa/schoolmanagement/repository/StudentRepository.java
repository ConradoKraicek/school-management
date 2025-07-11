package com.empresa.schoolmanagement.repository;

import com.empresa.schoolmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
