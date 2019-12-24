package com.codegym.quiz.service.impl;

import com.codegym.quiz.model.Category;
import com.codegym.quiz.model.Question;
import com.codegym.quiz.model.Quiz;
import com.codegym.quiz.repository.QuestionRepository;
import com.codegym.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Iterable<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public void save(Question model) {
        questionRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public Iterable<Question> findAllByCategory(Category category) {
        return questionRepository.findAllByCategory(category);
    }

    @Override
    public Iterable<Question> findAllByStatusIsTrue() {
        return questionRepository.findAllByStatusIsTrue();
    }

    @Override
    public Iterable<Question> findAllByQuiz(Quiz quiz) {
        return questionRepository.findAllByQuiz(quiz);
    }

    @Override
    public Iterable<Question> findAllByQuizIsNull() {
        return questionRepository.findAllByQuizIsNull();
    }
}
