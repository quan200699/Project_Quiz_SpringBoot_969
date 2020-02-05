package com.codegym.quiz.controller;


import com.codegym.quiz.model.Exam;
import com.codegym.quiz.model.Result;
import com.codegym.quiz.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ResultController {
    @Autowired
    private ResultService resultService;

    @GetMapping("/findAllResultByExam")
    public ResponseEntity<Iterable<Result>> findAllResultByExam(@RequestBody Exam exam){

    }
}
