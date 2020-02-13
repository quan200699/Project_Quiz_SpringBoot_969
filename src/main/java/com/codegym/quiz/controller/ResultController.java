package com.codegym.quiz.controller;


import com.codegym.quiz.model.*;
import com.codegym.quiz.service.ExamService;
import com.codegym.quiz.service.ResultService;
import com.codegym.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ResultController {
    @Autowired
    private ResultService resultService;

    @Autowired
    private ExamService examService;

    @Autowired
    private UserService userService;

    @GetMapping("/findAllResultByExam")
    public ResponseEntity<Iterable<Result>> findAllResultByExam(@RequestParam("exam") String exam) {
        Exam currentExam = examService.findByName(exam);
        if (currentExam == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Result> results = resultService.findAllByExam(currentExam);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/findAllResultByUser")
    public ResponseEntity<Iterable<Result>> findAllResultByUser(@RequestParam("user") String user) {
        User currentUser = userService.findByUsername(user);
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Result> result = resultService.findAllByUser(currentUser);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/results")
    public ResponseEntity<Result> createResult(@RequestBody Result result) {
        return new ResponseEntity<>(resultService.save(result), HttpStatus.CREATED);
    }

    @GetMapping("/results/{id}")
    public ResponseEntity<Result> getResult(@PathVariable Long id) {
        Optional<Result> result = resultService.findById(id);
        return result.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findResultByExamAndUser")
    public ResponseEntity<Result> findResultByExamAndUser(@RequestParam("exam") String exam, @RequestParam("user") String user) {
        Exam currentExam = examService.findByName(exam);
        if (exam == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User currentUser = userService.findByUsername(user);
        if (currentExam == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Result result = resultService.findByExamAndUser(currentExam, currentUser);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
