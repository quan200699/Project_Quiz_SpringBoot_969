package com.codegym.quiz.repository;

import com.codegym.quiz.model.Category;
import com.codegym.quiz.model.Question;
import com.codegym.quiz.model.Quiz;
import com.codegym.quiz.model.TypeOfQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Iterable<Question> findAllByCategoryAndStatusIsTrue(Category category);

    Iterable<Question> findAllByTypeOfQuestionAndStatusIsTrue(TypeOfQuestion typeOfQuestion);

    Iterable<Question> findAllByTypeOfQuestionAndCategoryAndStatusIsTrue(TypeOfQuestion typeOfQuestion, Category category);

    Iterable<Question> findAllByStatusIsTrue();

    Iterable<Question> findAllByQuiz(Quiz quiz);

    Iterable<Question> findAllByQuizIsNullAndStatusIsTrue();

    Iterable<Question> findAllByContentContainingAndStatusIsTrue(String content);

    Iterable<Question> findAllByContentContainingAndTypeOfQuestionAndCategoryAndStatusIsTrue(String content, TypeOfQuestion typeOfQuestion, Category category);

    Iterable<Question> findAllByContentContainingAndCategoryAndStatusIsTrue(String content, Category category);

    Iterable<Question> findAllByContentContainingAndTypeOfQuestionAndStatusIsTrue(String content, TypeOfQuestion typeOfQuestion);

    Iterable<Question> findAllByQuizIsNullAndContentContainingAndStatusIsTrue(String content);

    Iterable<Question> findAllByQuizIsNullAndCategoryAndStatusIsTrue(Category category);

    Iterable<Question> findAllByQuizIsNullAndTypeOfQuestionAndStatusIsTrue(TypeOfQuestion typeOfQuestion);

    Iterable<Question> findAllByQuizIsNullAndCategoryAndTypeOfQuestionAndStatusIsTrue(Category category, TypeOfQuestion typeOfQuestion);

    Iterable<Question> findAllByQuizIsNullAndContentContainingAndCategoryAndStatusIsTrue(String content, Category category);

    Iterable<Question> findAllByQuizIsNullAndContentContainingAndTypeOfQuestionAndStatusIsTrue(String content, TypeOfQuestion typeOfQuestion);

    Iterable<Question> findAllByQuizIsNullAndContentContainingAndCategoryAndTypeOfQuestionAndStatusIsTrue(String content, Category category, TypeOfQuestion typeOfQuestion);
}