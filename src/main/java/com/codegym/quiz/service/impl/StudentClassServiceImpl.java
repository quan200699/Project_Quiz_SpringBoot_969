package com.codegym.quiz.service.impl;

import com.codegym.quiz.model.StudentClass;
import com.codegym.quiz.repository.StudentClassRepository;
import com.codegym.quiz.service.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentClassServiceImpl implements StudentClassService {
    @Autowired
    private StudentClassRepository studentClassRepository;

    @Override
    public Iterable<StudentClass> findAll() {
        return studentClassRepository.findAll();
    }

    @Override
    public Optional<StudentClass> findById(Long id) {
        return studentClassRepository.findById(id);
    }

    @Override
    public void save(StudentClass model) {
        studentClassRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        studentClassRepository.deleteById(id);
    }

    @Override
    public StudentClass findByName(String name) {
        return studentClassRepository.findByName(name);
    }
}
