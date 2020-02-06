package com.codegym.quiz.service;

import com.codegym.quiz.model.StudentClass;

public interface StudentClassService extends GeneralService<StudentClass> {
    StudentClass findByName(String name);
}
