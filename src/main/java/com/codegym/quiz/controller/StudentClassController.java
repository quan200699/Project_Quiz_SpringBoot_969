package com.codegym.quiz.controller;

import com.codegym.quiz.model.Category;
import com.codegym.quiz.model.StudentClass;
import com.codegym.quiz.service.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class StudentClassController {
    @Autowired
    private StudentClassService studentClassService;

    @GetMapping("/classes")
    public ResponseEntity<Iterable<StudentClass>> findAllClasses() {
        Iterable<StudentClass> studentClasses = studentClassService.findAll();
        return new ResponseEntity<>(studentClasses, HttpStatus.OK);
    }

    @GetMapping("/classes/{id}")
    public ResponseEntity<StudentClass> getClass(@PathVariable Long id) {
        Optional<StudentClass> studentClassOptional = studentClassService.findById(id);
        return studentClassOptional.map(studentClass -> new ResponseEntity<>(studentClass, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/classes")
    public ResponseEntity<StudentClass> createClass(@Valid @RequestBody StudentClass studentClass, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        studentClassService.save(studentClass);
        return new ResponseEntity<>(studentClass, HttpStatus.OK);
    }

    @PutMapping("/classes/{id}")
    public ResponseEntity<StudentClass> updateClass(@Valid @RequestBody StudentClass studentClass, @PathVariable Long id, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<StudentClass> studentClassOptional = studentClassService.findById(id);
        if (!studentClassOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentClass.setId(studentClassOptional.get().getId());
        studentClassService.save(studentClass);
        return new ResponseEntity<>(studentClass, HttpStatus.OK);
    }

    @DeleteMapping("/classes/{id}")
    public ResponseEntity<StudentClass> deleteClass(@PathVariable Long id) {
        Optional<StudentClass> studentClass = studentClassService.findById(id);
        if (studentClass.isPresent()) {
            studentClassService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
