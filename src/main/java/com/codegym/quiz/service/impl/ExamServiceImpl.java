package com.codegym.quiz.service.impl;

import com.codegym.quiz.model.Exam;
import com.codegym.quiz.repository.ExamRepository;
import com.codegym.quiz.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;

    public ExamServiceImpl() {
    }

    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Iterable<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public Optional<Exam> findById(Long id) {
        return examRepository.findById(id);
    }

    @Override
    public Exam save(Exam model) {
       return examRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        examRepository.deleteById(id);
    }

    @Override
    public Exam findByName(String exam) {
        return examRepository.findByName(exam);
    }
}
