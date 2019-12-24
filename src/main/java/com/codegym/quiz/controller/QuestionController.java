package com.codegym.quiz.controller;

import com.codegym.quiz.model.*;
import com.codegym.quiz.service.AnswerService;
import com.codegym.quiz.service.QuestionService;
import com.codegym.quiz.service.TypeOfQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private TypeOfQuestionService typeOfQuestionService;

    @GetMapping("/questions")
    public ResponseEntity<Iterable<Question>> showQuestionList(Category category) {
        Iterable<Question> questions;
        if (category.getId() != null) {
            questions = questionService.findAllByCategory(category);
        } else {
            questions = questionService.findAll();
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/findAllQuestionByQuiz")
    public ResponseEntity<Iterable<Question>> findAllByQuiz(Quiz quiz) {
        Iterable<Question> questions;
        if (quiz == null) {
            questions = questionService.findAllByQuizIsNull();
        } else {
            questions = questionService.findAllByQuiz(quiz);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/questionStatusIsTrue")
    public ResponseEntity<Iterable<Question>> showQuestionStatusIsTrue() {
        List<TypeOfQuestion> typeOfQuestions = (List<TypeOfQuestion>) typeOfQuestionService.findAll();
        if (typeOfQuestions.isEmpty()) {
            TypeOfQuestion typeOfQuestion = new TypeOfQuestion();
            typeOfQuestion.setId(1L);
            typeOfQuestion.setName("Chọn đáp án chính xác nhất");
            typeOfQuestionService.save(typeOfQuestion);
            typeOfQuestion = new TypeOfQuestion();
            typeOfQuestion.setId(2L);
            typeOfQuestion.setName("Chọn nhiều đáp án");
            typeOfQuestionService.save(typeOfQuestion);
        }
        Iterable<Question> questions = questionService.findAllByStatusIsTrue();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping("/questions")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        questionService.save(question);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<Question> questionDetail(@PathVariable Long id) {
        Optional<Question> questionOptional = questionService.findById(id);
        return questionOptional.map(question -> new ResponseEntity<>(question, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/questions/{id}")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question, @PathVariable Long id) {
        Optional<Question> questionOptional = questionService.findById(id);
        if (!questionOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        question.setId(questionOptional.get().getId());
        questionService.save(question);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable Long id) {
        Optional<Question> question = questionService.findById(id);
        if (question.isPresent()) {
            Iterable<Answer> answers = answerService.findAllByQuestion(question.get());
            for (Answer answer : answers) {
                answerService.remove(answer.getId());
            }
            questionService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
