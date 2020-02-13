package com.codegym.quiz.controller;

import com.codegym.quiz.model.TypeOfQuestion;
import com.codegym.quiz.service.TypeOfQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypeOfQuestionControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TypeOfQuestionService typeOfQuestionService;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        TypeOfQuestion typeOfQuestion1 = typeOfQuestionService.save(new TypeOfQuestion("Choose the best answer"));
        TypeOfQuestion typeOfQuestion2 = typeOfQuestionService.save(new TypeOfQuestion("Multi choice"));
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(value = "admin", roles = {"ADMIN"})
    @DisplayName("find all return status 200 with role admin")
    @Test
    public void findAll_whenGetTypeOfQuestionsWithRoleAdmin_thenReturnStatus200()
            throws Exception {
        mvc.perform(get("/typeOfQuestions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "user", roles = {"USER"})
    @DisplayName("find all return status 200 with role user")
    @Test
    public void findAll_whenGetTypeOfQuestionsWithRoleUser_thenReturnStatus200()
            throws Exception {
        mvc.perform(get("/typeOfQuestions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "tutor", roles = {"TUTOR"})
    @DisplayName("find all return status 200 with role tutor")
    @Test
    public void findAll_whenGetTypeOfQuestionsWithRoleTutor_thenReturnStatus200()
            throws Exception {
        mvc.perform(get("/typeOfQuestions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "tutor", roles = {"TUTOR"})
    @DisplayName("find by id return status 200 with role tutor")
    @Test
    public void findById_whenGetTypeOfQuestionDetailWithRoleTutor_thenReturnStatus200()
            throws Exception {
        mvc.perform(get("/typeOfQuestions/{id}",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @WithMockUser(value = "admin", roles = {"ADMIN"})
    @DisplayName("find by id return status 200 with role admin")
    @Test
    public void findById_whenGetTypeOfQuestionDetailWithRoleAdmin_thenReturnStatus200()
            throws Exception {
        mvc.perform(get("/typeOfQuestions/{id}",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @WithMockUser(value = "user", roles = {"USER"})
    @DisplayName("find by id return status 200 with role user")
    @Test
    public void findById_whenGetTypeOfQuestionDetailWithRoleUser_thenReturnStatus200()
            throws Exception {
        mvc.perform(get("/typeOfQuestions/{id}",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}
