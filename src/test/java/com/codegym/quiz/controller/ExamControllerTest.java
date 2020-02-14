package com.codegym.quiz.controller;

import com.codegym.quiz.model.Exam;
import com.codegym.quiz.service.ExamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExamControllerTest {
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ExamService examService;

    private MockMvc mvc;

    private List<Exam> exams;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        Exam exam1 = examService.save(new Exam("No 1"));
        Exam exam2 = examService.save(new Exam("No 2"));
        exams = new ArrayList<>();
        exams.add(exam1);
        exams.add(exam2);
    }

    @WithMockUser(value = "admin", roles = {"ADMIN"})
    @DisplayName("find all return status 200 with role admin")
    @Test
    public void findAll_whenGetExamsWithRoleAdmin_thenReturnStatus200()
            throws Exception {
        given(examService.findAll()).willReturn(exams);
        mvc.perform(get("/exams")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @WithMockUser(value = "user", roles = {"USER"})
    @DisplayName("find all return status 200 with role user")
    @Test
    public void findAll_whenGetExamsWithRoleUser_thenReturnStatus200()
            throws Exception {
        given(examService.findAll()).willReturn(exams);
        mvc.perform(get("/exams")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
