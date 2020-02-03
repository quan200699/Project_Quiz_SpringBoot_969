package com.codegym.quiz.controller;

import com.codegym.quiz.model.Exam;
import com.codegym.quiz.service.ExamService;
import com.codegym.quiz.service.UserService;
import com.codegym.quiz.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ExamController {
    @Autowired
    private Environment env;

    @Autowired
    private ExamService examService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @GetMapping("/exams")
    public ResponseEntity<Iterable<Exam>> listExam(){
        Iterable<Exam> exams = examService.findAll();
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }
}
