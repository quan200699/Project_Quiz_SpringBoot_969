package com.codegym.quiz.repository;

import com.codegym.quiz.model.TypeOfQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeOfQuestionRepository extends JpaRepository<TypeOfQuestion, Long> {
    TypeOfQuestion findByName(String typeOfQuestionName);
}
