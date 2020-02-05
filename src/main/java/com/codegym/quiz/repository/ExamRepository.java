package com.codegym.quiz.repository;

import com.codegym.quiz.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    Exam findByName(String exam);
}
