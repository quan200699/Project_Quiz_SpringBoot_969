package com.codegym.quiz.service;

import com.codegym.quiz.model.Category;
import com.codegym.quiz.model.Question;
import com.codegym.quiz.model.Quiz;
import com.codegym.quiz.model.TypeOfQuestion;

public interface QuestionService extends GeneralService<Question> {
    Iterable<Question> findAllByCategoryAndStatusIsTrue(Category category);

    Iterable<Question> findAllByTypeOfQuestionAndStatusIsTrue(TypeOfQuestion typeOfQuestion);

    Iterable<Question> findAllByStatusIsTrue();

    Iterable<Question> findAllByQuiz(Quiz quiz);

    Iterable<Question> findAllByQuizIsNullAndStatusIsTrue();
}