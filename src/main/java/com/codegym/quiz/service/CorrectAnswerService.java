package com.codegym.quiz.service;

import com.codegym.quiz.model.CorrectAnswer;
import com.codegym.quiz.model.Question;

public interface CorrectAnswerService extends GeneralService<CorrectAnswer> {
    Iterable<CorrectAnswer> findAllByQuestion(Question question);
}
