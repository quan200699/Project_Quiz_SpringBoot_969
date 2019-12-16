package com.codegym.quiz.service;

import com.codegym.quiz.model.Category;
import com.codegym.quiz.model.Question;

public interface QuestionService extends GeneralService<Question> {
    Iterable<Question> findAllByCategory(Category category);

    Iterable<Question> findAllByStatusIsTrue();
}