package com.codegym.quiz.repository;

import com.codegym.quiz.model.TypeOfQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TypeOfQuestionRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TypeOfQuestionRepository typeOfQuestionRepository;

    @BeforeEach
    public void init() {
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

    @DisplayName("type of question repository findByName method can return a type of question not null")
    @Test
    public void whenFindByName_thenReturnTypeOfQuestionNotNull() {
        assertThat(typeOfQuestionRepository.findByName("Chọn đáp án chính xác nhất")).isNotNull();
    }
}
