package com.codegym.quiz.repository;

import com.codegym.quiz.model.Category;
import com.codegym.quiz.model.Question;
import com.codegym.quiz.model.Quiz;
import com.codegym.quiz.model.TypeOfQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Iterable<Question> findAllByCategoryAndStatusIsTrue(Category category);

    Iterable<Question> findAllByTypeOfQuestionAndStatusIsTrue(TypeOfQuestion typeOfQuestion);

    Iterable<Question> findAllByTypeOfQuestionAndCategoryStAndStatusIsTrue(TypeOfQuestion typeOfQuestion, Category category);

    Iterable<Question> findAllByStatusIsTrue();

    Iterable<Question> findAllByQuiz(Quiz quiz);

    Iterable<Question> findAllByQuizIsNullAndStatusIsTrue();
}