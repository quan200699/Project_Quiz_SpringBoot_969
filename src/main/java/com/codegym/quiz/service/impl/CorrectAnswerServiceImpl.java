package com.codegym.quiz.service.impl;

import com.codegym.quiz.model.CorrectAnswer;
import com.codegym.quiz.model.Question;
import com.codegym.quiz.repository.CorrectAnswerRepository;
import com.codegym.quiz.service.CorrectAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
    public CorrectAnswer save(CorrectAnswer model) {
        return correctAnswerRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        correctAnswerRepository.deleteById(id);
    }

    @Override
    public Iterable<CorrectAnswer> findAllByQuestion(Question question) {
        return correctAnswerRepository.findAllByQuestion(question);
    }
}
