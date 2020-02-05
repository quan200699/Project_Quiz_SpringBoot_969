package com.codegym.quiz.repository;

import com.codegym.quiz.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
