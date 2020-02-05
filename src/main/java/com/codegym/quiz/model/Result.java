package com.codegym.quiz.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double point;

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private User user;
}
