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

    public TypeOfQuestionServiceImpl() {
    }

    public TypeOfQuestionServiceImpl(TypeOfQuestionRepository typeOfQuestionRepository) {
        this.typeOfQuestionRepository = typeOfQuestionRepository;
    }

    @Override
    public Iterable<TypeOfQuestion> findAll() {
        return typeOfQuestionRepository.findAll();
    }

    @Override
    public Optional<TypeOfQuestion> findById(Long id) {
        return typeOfQuestionRepository.findById(id);
    }

    @Override
    public TypeOfQuestion save(TypeOfQuestion model) {
        return typeOfQuestionRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        typeOfQuestionRepository.deleteById(id);
    }

    @Override
    public TypeOfQuestion findByName(String typeOfQuestionName) {
        return typeOfQuestionRepository.findByName(typeOfQuestionName);
    }
}
