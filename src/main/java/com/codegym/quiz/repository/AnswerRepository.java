package com.codegym.quiz.repository;

import com.codegym.quiz.model.Answer;
import com.codegym.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Iterable<Answer> findAllByQuestion(Question question);
}
