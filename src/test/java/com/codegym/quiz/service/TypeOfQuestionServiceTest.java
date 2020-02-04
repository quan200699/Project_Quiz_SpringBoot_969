package com.codegym.quiz.service;

import com.codegym.quiz.model.TypeOfQuestion;
import com.codegym.quiz.repository.TypeOfQuestionRepository;
import com.codegym.quiz.service.impl.TypeOfQuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

public class TypeOfQuestionServiceTest {
    private TypeOfQuestionRepository typeOfQuestionRepository = Mockito.mock(TypeOfQuestionRepository.class);
    private TypeOfQuestionService typeOfQuestionService = new TypeOfQuestionServiceImpl(typeOfQuestionRepository);

    @BeforeEach
    void init() {
        TypeOfQuestion typeOfQuestion = new TypeOfQuestion();
        typeOfQuestion.setName("Chọn đáp án chính xác nhất");
        doReturn(Optional.of(typeOfQuestion)).when(typeOfQuestionRepository).findById(1L);
        List<TypeOfQuestion> typeOfQuestions = Arrays.asList(typeOfQuestion);
        doReturn(typeOfQuestions).when(typeOfQuestionRepository).findAll();
    }

    @DisplayName("findAll method in typeOfQuestionService can return a list not null")
    @Test
    public void whenFindAll_thenReturnListNotNull() {
        assertThat(typeOfQuestionService.findAll()).isNotNull();
    }
}
