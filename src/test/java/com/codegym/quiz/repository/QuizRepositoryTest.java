package com.codegym.quiz.repository;

import com.codegym.quiz.model.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class QuizRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private QuizRepository quizRepository;

    @BeforeEach
    public void init() {
        Quiz quiz = new Quiz("01", 45);
        entityManager.persist(quiz);
        entityManager.flush();
    }

    @DisplayName("findAll can return list (is not null)")
    @Test
    public void whenFindAll_thenReturnListNotNull() {
        assertThat(quizRepository.findAll()).isNotNull();
    }
}
