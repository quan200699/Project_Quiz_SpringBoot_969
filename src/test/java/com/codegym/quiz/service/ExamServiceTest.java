package com.codegym.quiz.service;

import com.codegym.quiz.model.Exam;
import com.codegym.quiz.repository.ExamRepository;
import com.codegym.quiz.service.impl.ExamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

public class ExamServiceTest {
    private ExamRepository examRepository = Mockito.mock(ExamRepository.class);
    private ExamService examService = new ExamServiceImpl(examRepository);

    @BeforeEach
    void init() {
        Exam exam = new Exam();
        exam.setName("No 01");
        doReturn(Optional.of(exam)).when(examRepository).findById(1L);
        List<Exam> exams = Arrays.asList(exam);
        doReturn(exams).when(examRepository).findAll();
    }

    @DisplayName("find all can return a list not null")
    @Test
    public void whenFindAll_thenReturnAListNotNull() {
        assertThat(examService.findAll()).isNotNull();
    }

    @DisplayName("find all can return a list has one element")
    @Test
    public void whenFindAll_thenReturnAListHasOneElement() {
        assertThat(examService.findAll()).hasSize(1);
    }
}
