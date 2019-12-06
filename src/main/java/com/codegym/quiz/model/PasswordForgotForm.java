package com.codegym.quiz.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class PasswordForgotForm {
    @Email
    @NotEmpty
    private String email;
}
