package com.codegym.quiz.repository;

import com.codegym.quiz.model.Exam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExamRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExamRepository examRepository;

    @BeforeEach
    void init() {
        Exam exam = new Exam();
        exam.setName("Module 1");
        entityManager.persist(exam);
        entityManager.flush();
    }

    @DisplayName("find all can return a list not null")
    @Test
    public void whenFindAll_thenReturnAListNotNull() {
        assertThat(examRepository.findAll()).isNotNull();
    }

    @DisplayName("find all can return a list has 1 element")
    @Test
    public void whenFindAll_thenReturnAListHasOneElement() {
        assertThat(examRepository.findAll()).hasSize(1);
    }

    @DisplayName("findById can return a exam not null")
    @Test
    public void whenFindById_thenReturnAExamNotNull() {
        assertThat(examRepository.findById(1L)).isNotNull();
    }
}
