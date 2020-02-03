package com.codegym.quiz.controller;

import com.codegym.quiz.model.Exam;
import com.codegym.quiz.service.ExamService;
import com.codegym.quiz.service.UserService;
import com.codegym.quiz.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @GetMapping("/exams/{id}")
    public ResponseEntity<Exam> examDetail(@PathVariable Long id){
        Optional<Exam> examOptional = examService.findById(id);
        return examOptional.map(exam -> new ResponseEntity<>(exam, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/exams")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam){
        if (exam.getMinutes().isBefore(exam.getStartedDate())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        examService.save(exam);
        return new ResponseEntity<>(exam,HttpStatus.CREATED);
    }
}
