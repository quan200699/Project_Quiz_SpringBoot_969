package com.codegym.quiz.repository;

import com.codegym.quiz.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Iterable<Category> findAllByName(String categoryName);
}
