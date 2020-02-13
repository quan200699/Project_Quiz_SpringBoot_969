package com.codegym.quiz.controller;

import com.codegym.quiz.model.Exam;
import com.codegym.quiz.model.User;
import com.codegym.quiz.service.ExamService;
import com.codegym.quiz.service.UserService;
import com.codegym.quiz.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
        return new ResponseEntity<>(examService.save(exam),HttpStatus.CREATED);
    }

    @PutMapping("/exams/{id}")
    public ResponseEntity<Exam> updateExam(@RequestBody Exam exam, @PathVariable Long id) {
        Optional<Exam> examOptional = examService.findById(id);
        if (!examOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        exam.setId(examOptional.get().getId());
        exam.setParticipants(examOptional.get().getParticipants());
        return new ResponseEntity<>(examService.save(exam), HttpStatus.OK);
    }

    @DeleteMapping("/exams/{id}")
    public ResponseEntity<Exam> deleteExam(@PathVariable Long id) {
        examService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/doExam/{id}")
    public ResponseEntity<Exam> doExam(@PathVariable Long id){
        Optional<Exam> examOptional = examService.findById(id);
        if (!examOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LocalDateTime currentTime = LocalDateTime.now();
        if (examOptional.get().getStartedDate().isAfter(currentTime)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(examOptional.get(),HttpStatus.OK);
    }

    @PostMapping("/join/{examId}")
    public ResponseEntity<Exam> joinExam(@RequestBody User user,@PathVariable Long examId){
        Optional<Exam> examOptional = examService.findById(examId);
        if (!examOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<User> userOptional = userService.findById(user.getId());
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        examOptional.get().getParticipants().add(userOptional.get());
        emailService.sendEmail(userOptional.get().getEmail(), env.getProperty("examSubject"), env.getProperty("linkExam") + examOptional.get().getId());
        examService.save(examOptional.get());
        return new ResponseEntity<>(examOptional.get(),HttpStatus.OK);
    }
}
