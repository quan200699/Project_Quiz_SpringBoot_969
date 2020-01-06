package com.codegym.quiz.controller;

import com.codegym.quiz.model.*;
import com.codegym.quiz.service.*;
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
    private QuizService quizService;

    @Autowired
    private TypeOfQuestionService typeOfQuestionService;

    @Autowired
    private CorrectAnswerService correctAnswerService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/questions")
    public ResponseEntity<Iterable<Question>> showQuestionList(Category category) {
        Iterable<Question> questions;
        if (category.getId() != null) {
            questions = questionService.findAllByCategoryAndStatusIsTrue(category);
        } else {
            questions = questionService.findAll();
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/findAllQuestionByQuiz/{id}")
    public ResponseEntity<Iterable<Question>> findAllByQuiz(@PathVariable Long id) {
        Optional<Quiz> quizOptional = quizService.findById(id);
        if (!quizOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Question> questions = questionService.findAllByQuiz(quizOptional.get());
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/findAllQuestionByQuizNull")
    public ResponseEntity<Iterable<Question>> findAllByQuizNull() {
        Iterable<Question> questions = questionService.findAllByQuizIsNullAndStatusIsTrue();
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
            Iterable<CorrectAnswer> correctAnswers = correctAnswerService.findAllByQuestion(question.get());
            for (Answer answer : answers) {
                answerService.remove(answer.getId());
            }
            for (CorrectAnswer correctAnswer : correctAnswers) {
                correctAnswerService.remove(correctAnswer.getId());
            }
            questionService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("findAllQuestionByCategory")
    public ResponseEntity<Iterable<Question>> findAllQuestionByCategory(@RequestParam("category") String category) {
        Category currentCategory = categoryService.findByName(category);
        if (currentCategory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Question> questions = questionService.findAllByCategoryAndStatusIsTrue(currentCategory);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("findAllQuestionByTypeOfQuestion")
    public ResponseEntity<Iterable<Question>> findAllQuestionByTypeOfQuestion(@RequestParam("typeOfQuestion") String typeOfQuestion) {
        TypeOfQuestion currentTypeOfQuestion = typeOfQuestionService.findByName(typeOfQuestion);
        if (currentTypeOfQuestion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Question> questions = questionService.findAllByTypeOfQuestionAndStatusIsTrue(currentTypeOfQuestion);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
    @GetMapping("findAllByTypeOfQuestionAndCategory")
    public ResponseEntity<Iterable<Question>> findAllByTypeOfQuestionAndCategory(@RequestParam("typeOfQuestion") String typeOfQuestion, @RequestParam("category") String category) {
        TypeOfQuestion currentTypeOfQuestion = typeOfQuestionService.findByName(typeOfQuestion);
        Category currentCategory = categoryService.findByName(category);
        if (currentTypeOfQuestion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (currentCategory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Question> questions = questionService.findAllByTypeOfQuestionAndCategoryAndStatusIsTrue(currentTypeOfQuestion,currentCategory);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }
    @GetMapping("findAllByContentContaining")
    public ResponseEntity<Iterable<Question>> findAllByContentContaining(@RequestParam("content") String content) {
        if (content == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Question> questions = questionService.findAllByContentContainingAndStatusIsTrue(content);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

}
