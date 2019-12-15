package com.codegym.quiz.service;

import com.codegym.quiz.model.Answer;
import com.codegym.quiz.model.Question;

public interface AnswerService extends GeneralService<Answer> {
    Iterable<Answer> findAllByQuestion(Question question);
}
