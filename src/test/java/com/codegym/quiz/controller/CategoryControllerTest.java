package com.codegym.quiz.controller;

import com.codegym.quiz.model.Category;
import com.codegym.quiz.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static com.codegym.quiz.model.StaticVariable.asJsonString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CategoryService categoryService;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        Category category1 = categoryService.save(new Category("category 01"));
        Category category2 = categoryService.save(new Category("category 02"));
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(value = "admin", roles = {"ADMIN"})
    @DisplayName("find all return status 200 with role admin")
    @Test
    public void findAll_whenGetCategoriesWithRoleAdmin_thenReturnStatus200()
            throws Exception {
        mvc.perform(get("/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "user", roles = {"USER"})
    @DisplayName("find all return status 200 with role user")
    @Test
    public void findAll_whenGetCategoriesWithRoleUser_thenReturnStatus200()
            throws Exception {
        mvc.perform(get("/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "tutor", roles = {"TUTOR"})
    @DisplayName("find all return status 200 with role tutor")
    @Test
    public void findAll_whenGetCategoriesWithRoleTutor_thenReturnStatus200()
            throws Exception {
        mvc.perform(get("/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "tutor", roles = {"TUTOR"})
    @DisplayName("create category return status 200 with role tutor")
    @Test
    public void create_whenCreateCategoryWithRoleTutor_thenReturnStatus200()
            throws Exception {
        Category category = new Category();
        category.setName("Hello");
        Iterable<Category> categories = categoryService.findAll();
        mvc.perform(post("/categories")
                .content(asJsonString(category))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @WithMockUser(value = "admin", roles = {"admin"})
    @DisplayName("create category return status 403 with role admin")
    @Test
    public void create_whenCreateCategoryWithRoleAdmin_thenReturnStatus403()
            throws Exception {
        Category category = new Category();
        category.setName("Hello");
        mvc.perform(post("/categories")
                .content(asJsonString(category))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(value = "user", roles = {"user"})
    @DisplayName("create category return status 403 with role user")
    @Test
    public void create_whenCreateCategoryWithRoleUser_thenReturnStatus403()
            throws Exception {
        Category category = new Category();
        category.setName("Hello");
        mvc.perform(post("/categories")
                .content(asJsonString(category))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(value = "tutor", roles = {"TUTOR"})
    @DisplayName("update category return 200 with role tutor")
    @Test
    public void update_whenUpdateCategoryWithRoleTutor_thenReturnStatus200()
            throws Exception {
        Category category = new Category();
        category.setName("Hello");

        mvc.perform(put("/categories/1")
                .content(asJsonString(category))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @WithMockUser(value = "admin", roles = {"ADMIN"})
    @DisplayName("update category return 403 with role admin")
    @Test
    public void update_whenUpdateCategoryWithRoleAdmin_thenReturnStatus403()
            throws Exception {
        Category category = new Category();
        category.setName("Hello");
        mvc.perform(put("/categories/{id}", 1L)
                .content(asJsonString(category))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(value = "user", roles = {"USER"})
    @DisplayName("update category return 403 with role user")
    @Test
    public void update_whenUpdateCategoryWithRoleUser_thenReturnStatus403()
            throws Exception {
        Category category = new Category();
        category.setName("Hello");
        mvc.perform(put("/categories/{id}", 1L)
                .content(asJsonString(category))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(value = "tutor", roles = {"TUTOR"})
    @DisplayName("delete category return 204 with role tutor")
    @Test
    public void delete_whenDeleteCategoryWithRoleTutor_thenReturnStatus204()
            throws Exception {
        mvc.perform(delete("/categories/{id}", 2L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @WithMockUser(value = "admin", roles = {"ADMIN"})
    @DisplayName("delete category return 403 with role admin")
    @Test
    public void delete_whenDeleteCategoryWithRoleAdmin_thenReturnStatus403()
            throws Exception {
        mvc.perform(delete("/categories/{id}", 2L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(value = "user", roles = {"USER"})
    @DisplayName("delete category return 403 with role user")
    @Test
    public void delete_whenDeleteCategoryWithRoleUser_thenReturnStatus403()
            throws Exception {
        mvc.perform(delete("/categories/{id}", 2L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(value = "tutor", roles = {"TUTOR"})
    @DisplayName("delete category return 404 when category id not found with role tutor")
    @Test
    public void delete_whenDeleteCategoryWithRoleTutor_thenReturnStatus404()
            throws Exception {
        mvc.perform(delete("/categories/{id}", 0L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(value = "tutor", roles = {"TUTOR"})
    @DisplayName("get category return 200 with role tutor")
    @Test
    public void getDetail_whenGetCategoryWithRoleTutor_thenReturnStatus200()
            throws Exception {
        mvc.perform(get("/categories/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @WithMockUser(value = "admin", roles = {"ADMIN"})
    @DisplayName("get category return 200 with role admin")
    @Test
    public void getDetail_whenGetCategoryWithRoleAdmin_thenReturnStatus200()
            throws Exception {
        mvc.perform(get("/categories/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @WithMockUser(value = "user", roles = {"USER"})
    @DisplayName("get category return 200 with role user")
    @Test
    public void getDetail_whenGetCategoryWithRoleUser_thenReturnStatus200()
            throws Exception {
        mvc.perform(get("/categories/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @WithMockUser(value = "user", roles = {"USER"})
    @DisplayName("get category return 404 when id not found")
    @Test
    public void getDetail_whenGetCategoryWithRoleUser_thenReturnStatus404()
            throws Exception {
        mvc.perform(get("/categories/{id}", 0L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
