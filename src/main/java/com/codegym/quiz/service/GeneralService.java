package com.codegym.quiz.service;

import java.util.Optional;

public interface GeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T model);

    void remove(Long id);
}
