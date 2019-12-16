package com.codegym.quiz.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TypeOfQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
