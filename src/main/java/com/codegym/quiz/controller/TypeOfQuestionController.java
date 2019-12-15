package com.codegym.quiz.controller;

import com.codegym.quiz.model.TypeOfQuestion;
import com.codegym.quiz.service.TypeOfQuestionService;
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
public class TypeOfQuestionController {
    @Autowired
    private TypeOfQuestionService typeOfQuestionService;

    @GetMapping("/typeOfQuestions")
    public ResponseEntity<Iterable<TypeOfQuestion>> showAll() {
        Iterable<TypeOfQuestion> typeOfQuestions = typeOfQuestionService.findAll();
        return new ResponseEntity<>(typeOfQuestions, HttpStatus.OK);
    }

    @GetMapping("/typeOfQuestions/{id}")
    public ResponseEntity<TypeOfQuestion> getTypeOfQuestion(@PathVariable Long id) {
        Optional<TypeOfQuestion> typeOfQuestionOptional = typeOfQuestionService.findById(id);
        return typeOfQuestionOptional.map(typeOfQuestion -> new ResponseEntity<>(typeOfQuestion, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
