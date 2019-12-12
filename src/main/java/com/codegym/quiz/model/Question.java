package com.codegym.quiz.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String quiz;

    @Column(nullable = false)
    private String answerA;

    @Column(nullable = false)
    private String answerB;

    @Column(nullable = false)
    private String answerC;

    @Column(nullable = false)
    private String answerD;

    @Column(nullable = false)
    private String correctAnswer;

    @ManyToOne
    private TypeOfQuestion typeOfQuestion;

    @ManyToOne
    private Category category;
}
