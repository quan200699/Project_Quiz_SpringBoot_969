package com.codegym.quiz.controller;

import com.codegym.quiz.model.Quiz;
import com.codegym.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class QuizController {
    @Autowired
   private QuizService quizService;
    @GetMapping("/quizzes")
    public ResponseEntity<Iterable<Quiz>> listQuiz(){
        Iterable<Quiz> quizzes = quizService.findAll();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }
    @GetMapping("/quizzes/{id}")
    public ResponseEntity<Quiz> quizDetal(@PathVariable Long id) {
        Optional<Quiz> quizOptional = quizService.findById(id);
        return quizOptional.map(quiz -> new ResponseEntity<>(quiz,HttpStatus.OK)).
                orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/quizzes")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        quizService.save(quiz);
        return new ResponseEntity<>(quiz,HttpStatus.OK);
    }
    @PutMapping("/quizzes/{id}")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz,@PathVariable Long id) {
        Optional<Quiz> quizOptional = quizService.findById(id);
        if (!quizOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        quiz.setId(quizOptional.get().getId());
        quizService.save(quiz);
        return new ResponseEntity<>(quiz,HttpStatus.OK);
    }
    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<Quiz> deleteQuiz(@PathVariable Long id) {
        Optional<Quiz> quiz = quizService.findById(id);
        if (quiz.isPresent()){
            quizService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
