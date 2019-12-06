package com.codegym.quiz.controller;

import com.codegym.quiz.model.PasswordForgotForm;
import com.codegym.quiz.model.User;
import com.codegym.quiz.model.VerificationToken;
import com.codegym.quiz.service.UserService;
import com.codegym.quiz.service.VerificationTokenService;
import com.codegym.quiz.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.codegym.quiz.model.StaticVariable.SUBJECT_PASSWORD_FORGOT;
import static com.codegym.quiz.model.StaticVariable.TEXT_PASSWORD_FORGOT;

@RestController
@CrossOrigin("*")
public class ForgotPasswordController {
    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody PasswordForgotForm passwordForgotForm) {
        User user = userService.findByEmail(passwordForgotForm.getEmail());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        VerificationToken token = new VerificationToken(user);
        token.setExpiryDate(10);
        verificationTokenService.save(token);
        emailService.sendEmail(passwordForgotForm.getEmail(), SUBJECT_PASSWORD_FORGOT,
                TEXT_PASSWORD_FORGOT + env.getProperty("forgotPasswordLink") + token.getToken());
        return new ResponseEntity<>(passwordForgotForm, HttpStatus.OK);
    }
}
