package com.codegym.quiz.service.impl;

import com.codegym.quiz.model.CorrectAnswer;
import com.codegym.quiz.repository.CorrectAnswerRepository;
import com.codegym.quiz.service.CorrectAnswerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CorrectAnswerServiceImpl implements CorrectAnswerService {
    @Autowired
    private CorrectAnswerRepository correctAnswerRepository;

    @Override
    public Iterable<CorrectAnswer> findAll() {
        return correctAnswerRepository.findAll();
    }

    @Override
    public Optional<CorrectAnswer> findById(Long id) {
        return correctAnswerRepository.findById(id);
    }

    @Override
    public void save(CorrectAnswer model) {
        correctAnswerRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        correctAnswerRepository.deleteById(id);
    }
}
