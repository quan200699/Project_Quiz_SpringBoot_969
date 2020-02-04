package com.codegym.quiz.controller;

import com.codegym.quiz.model.Question;
import com.codegym.quiz.model.Quiz;
import com.codegym.quiz.model.User;
import com.codegym.quiz.service.QuestionService;
import com.codegym.quiz.service.QuizService;
import com.codegym.quiz.service.UserService;
import com.codegym.quiz.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/quizzes")
    public ResponseEntity<Iterable<Quiz>> listQuiz() {
        Iterable<Quiz> quizzes = quizService.findAll();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<Quiz> quizDetail(@PathVariable Long id) {
        Optional<Quiz> quizOptional = quizService.findById(id);
        return quizOptional.map(quiz -> new ResponseEntity<>(quiz, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/quizzes")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        quizService.save(quiz);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @PutMapping("/quizzes/{id}")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz, @PathVariable Long id) {
        Optional<Quiz> quizOptional = quizService.findById(id);
        if (!quizOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        quiz.setId(quizOptional.get().getId());
        quizService.save(quiz);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<Quiz> deleteQuiz(@PathVariable Long id) {
        Optional<Quiz> quiz = quizService.findById(id);
        if (quiz.isPresent()) {
            List<Question> questions = (List<Question>) questionService.findAllByQuiz(quiz.get());
            for (Question question : questions) {
                question.setQuiz(null);
                questionService.save(question);
            }
            quizService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
