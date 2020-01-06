package com.codegym.quiz.service;

import com.codegym.quiz.model.TypeOfQuestion;

public interface TypeOfQuestionService extends GeneralService<TypeOfQuestion> {
    Iterable<TypeOfQuestion> findAllByName(String typeOfQuestionName);
}
