package com.codegym.quiz.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Exam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime startedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "exam_participant",
            joinColumns = {@JoinColumn(name = "exam_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> participants;

    @ManyToOne
    private Quiz quiz;
}
