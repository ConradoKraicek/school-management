package com.empresa.schoolmanagement.repository;

import com.empresa.schoolmanagement.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
