package com.codegym.quiz.service.impl;

import com.codegym.quiz.model.TypeOfQuestion;
import com.codegym.quiz.repository.TypeOfQuestionRepository;
import com.codegym.quiz.service.TypeOfQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeOfQuestionServiceImpl implements TypeOfQuestionService {
    @Autowired
    private TypeOfQuestionRepository typeOfQuestionRepository;

    @Override
    public Iterable<TypeOfQuestion> findAll() {
        return typeOfQuestionRepository.findAll();
    }

    @Override
    public Optional<TypeOfQuestion> findById(Long id) {
        return typeOfQuestionRepository.findById(id);
    }

    @Override
    public void save(TypeOfQuestion model) {
        typeOfQuestionRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        typeOfQuestionRepository.deleteById(id);
    }

    @Override
    public Iterable<TypeOfQuestion> findAllByName(String typeOfQuestionName) {
        return typeOfQuestionRepository.findAllByName(typeOfQuestionName);
    }
}
