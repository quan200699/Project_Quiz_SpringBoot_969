package com.codegym.quiz.service.impl;

import com.codegym.quiz.model.Exam;
import com.codegym.quiz.model.Result;
import com.codegym.quiz.model.User;
import com.codegym.quiz.repository.ResultRepository;
import com.codegym.quiz.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Iterable<Result> findAllByExam(Exam exam) {
        return resultRepository.findAllByExam(exam);
    }

    @Override
    public Iterable<Result> findAllByUser(User user) {
        return resultRepository.findAllByUser(user);
    }

    @Override
    public Result findByExamAndUser(Exam exam, User user) {
        return resultRepository.findByExamAndUser(exam, user);
    }

    @Override
    public Iterable<Result> findAll() {
        return resultRepository.findAll();
    }

    @Override
    public Optional<Result> findById(Long id) {
        return resultRepository.findById(id);
    }

    @Override
    public void save(Result model) {
        resultRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        resultRepository.deleteById(id);
    }
}
