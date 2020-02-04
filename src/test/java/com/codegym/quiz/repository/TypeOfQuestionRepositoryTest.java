package com.codegym.quiz.repository;

import com.codegym.quiz.model.TypeOfQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TypeOfQuestionRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TypeOfQuestionRepository typeOfQuestionRepository;

    @BeforeEach
    void init() {
        TypeOfQuestion typeOfQuestion = new TypeOfQuestion();
        typeOfQuestion.setName("Chọn đáp án chính xác nhất");
        entityManager.persist(typeOfQuestion);
        entityManager.flush();
    }

    @DisplayName("type of question repository findAll method can return a list not null")
    @Test
    public void whenFindAll_thenReturnListNotNull() {
        assertThat(typeOfQuestionRepository.findAll()).isNotNull();
    }

    @DisplayName("type of question repository findAll method can return a list has 1 element")
    @Test
    public void whenFindAll_thenReturnListHas1Element() {
        List<TypeOfQuestion> typeOfQuestions = typeOfQuestionRepository.findAll();
        assertThat(typeOfQuestions).hasSize(1);
    }

    @DisplayName("type of question repository findById method can return a type of question has name Chọn đáp án chính xác nhất")
    @Test
    public void whenFindById_thenReturnTypeOfQuestion() {
        Optional<TypeOfQuestion> typeOfQuestion = typeOfQuestionRepository.findById(1L);
        assertThat(typeOfQuestion.get().getName()).isEqualTo("Chọn đáp án chính xác nhất");
    }
}
