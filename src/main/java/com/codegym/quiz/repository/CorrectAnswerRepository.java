package com.codegym.quiz.repository;

import com.codegym.quiz.model.CorrectAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrectAnswerRepository extends JpaRepository<CorrectAnswer, Long> {
}
