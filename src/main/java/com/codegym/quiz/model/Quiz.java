package com.codegym.quiz.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Quiz implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int minutes;

    public Quiz() {
    }

    public Quiz(String name, int minutes) {
        this.name = name;
        this.minutes = minutes;
    }
}
