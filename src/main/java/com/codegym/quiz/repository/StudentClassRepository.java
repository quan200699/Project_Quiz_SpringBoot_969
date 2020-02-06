package com.codegym.quiz.repository;

import com.codegym.quiz.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {
    StudentClass findByName(String name);
}
