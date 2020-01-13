package com.codegym.quiz.service;

import com.codegym.quiz.model.Quiz;
import com.codegym.quiz.model.User;

import java.util.Set;

public interface QuizService extends GeneralService<Quiz> {
    Iterable<Quiz> findAllByParticipants(Set<User> setParticipants);
}
