package com.codegym.quiz.controller;

import com.codegym.quiz.model.Answer;
import com.codegym.quiz.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @GetMapping("/answers")
    public ResponseEntity<Iterable<Answer>> showAll() {
        Iterable<Answer> answers = answerService.findAll();
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @GetMapping("/answers/{id}")
    public ResponseEntity<Answer> getAnswerDetail(@PathVariable Long id) {
        Optional<Answer> answer = answerService.findById(id);
        return answer.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
