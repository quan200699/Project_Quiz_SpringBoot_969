package com.codegym.quiz.repository;

import com.codegym.quiz.model.Category;
import com.codegym.quiz.model.Question;
import com.codegym.quiz.model.TypeOfQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Iterable<Question> findAllByCategory(Category category);

    Iterable<Question> findAllByStatusIsTrue();

    Question findQuestionByTypeOfQuestion(TypeOfQuestion typeOfQuestion);

}