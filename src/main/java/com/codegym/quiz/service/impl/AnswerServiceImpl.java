package com.codegym.quiz.service.impl;

import com.codegym.quiz.model.Answer;
import com.codegym.quiz.repository.AnswerRepository;
import com.codegym.quiz.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Iterable<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    public Optional<Answer> findById(Long id) {
        return answerRepository.findById(id);
    }

    @Override
    public void save(Answer model) {
        answerRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        answerRepository.deleteById(id);
    }
}
