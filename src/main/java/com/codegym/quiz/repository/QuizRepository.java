package com.codegym.quiz.repository;
import java.util.Set;

import com.codegym.quiz.model.Quiz;
import com.codegym.quiz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Iterable<Quiz> findAllByParticipants(Set<User> setParticipants);
}
