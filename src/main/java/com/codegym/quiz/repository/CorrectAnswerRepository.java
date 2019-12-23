package com.codegym.quiz.repository;

import com.codegym.quiz.model.CorrectAnswer;
import com.codegym.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrectAnswerRepository extends JpaRepository<CorrectAnswer, Long> {
    Iterable<CorrectAnswer> findAllByQuestion(Question question);
}
