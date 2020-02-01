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
    private Long id;

    private String content;

    private boolean status;

    @ManyToOne
    private TypeOfQuestion typeOfQuestion;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Quiz quiz;
}
