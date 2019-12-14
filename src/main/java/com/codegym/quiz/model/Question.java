package com.codegym.quiz.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quiz;

    @OneToMany
    private Set<Answer> answers;

    private String correctAnswer;

    @ManyToOne
    private TypeOfQuestion typeOfQuestion;

    @ManyToOne
    private Category category;
}
