package com.codegym.quiz.service;

import com.codegym.quiz.model.TypeOfQuestion;

public interface TypeOfQuestionService extends GeneralService<TypeOfQuestion> {
    TypeOfQuestion findByName(String typeOfQuestionName);
}
