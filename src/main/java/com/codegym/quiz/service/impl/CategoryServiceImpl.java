package com.codegym.quiz.service.impl;

import com.codegym.quiz.model.Category;
import com.codegym.quiz.repository.CategoryRepository;
import com.codegym.quiz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void save(Category model) {
        categoryRepository.save(model);

    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);

    }
}
