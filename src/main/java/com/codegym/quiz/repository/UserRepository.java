package com.codegym.quiz.repository;

import com.codegym.quiz.model.StudentClass;
import com.codegym.quiz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}
