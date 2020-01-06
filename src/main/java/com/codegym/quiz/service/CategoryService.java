package com.codegym.quiz.service;

import com.codegym.quiz.model.Category;

public interface CategoryService extends GeneralService<Category> {
    Iterable<Category> findAllByName(String categoryName);
}
