package com.codegym.quiz.service;

import com.codegym.quiz.model.Category;

public interface CategoryService extends GeneralService<Category>, MyService<Category> {
    Category findByName(String categoryName);
}
