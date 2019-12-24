package com.codegym.quiz.service;

import com.codegym.quiz.model.Category;
import com.codegym.quiz.model.Question;
import com.codegym.quiz.model.Quiz;

public interface QuestionService extends GeneralService<Question> {
    Iterable<Question> findAllByCategory(Category category);

    Iterable<Question> findAllByStatusIsTrue();

    Iterable<Question> findAllByQuiz(Quiz quiz);

    Iterable<Question> findAllByQuizIsNull();
}