package com.codegym.quiz.service;

import com.codegym.quiz.model.Exam;
import com.codegym.quiz.model.Result;
import com.codegym.quiz.model.User;

public interface ResultService extends GeneralService<Result> {
    Iterable<Result> findAllByExam(Exam exam);
    Result findAllByUser(User user);
}
