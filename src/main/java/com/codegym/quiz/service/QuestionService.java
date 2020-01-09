package com.codegym.quiz.service;

import com.codegym.quiz.model.Category;
import com.codegym.quiz.model.Question;
import com.codegym.quiz.model.Quiz;
import com.codegym.quiz.model.TypeOfQuestion;

public interface QuestionService extends GeneralService<Question> {
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
}