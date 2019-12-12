package com.codegym.quiz.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class TypeOfQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(targetEntity = Question.class)
    private Set<Question> questions;
}
