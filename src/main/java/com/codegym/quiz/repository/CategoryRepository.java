package com.codegym.quiz.repository;

import com.codegym.quiz.model.Category;
import com.codegym.quiz.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
