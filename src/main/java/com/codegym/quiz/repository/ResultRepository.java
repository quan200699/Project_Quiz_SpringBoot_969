package com.codegym.quiz.repository;

import com.codegym.quiz.model.Exam;
import com.codegym.quiz.model.Result;
import com.codegym.quiz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
    Iterable<Result> findAllByExam(Exam exam);

    Iterable<Result> findAllByUser(User user);

    Result findByExamAndUser(Exam exam, User user);
}
