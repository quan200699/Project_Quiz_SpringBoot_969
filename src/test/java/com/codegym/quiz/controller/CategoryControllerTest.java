//package com.codegym.quiz.controller;
//
//import com.codegym.quiz.model.Category;
//import com.codegym.quiz.service.CategoryService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(CategoryController.class)
//public class CategoryControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private CategoryService categoryService;
//
//    @BeforeEach
//    void init() {
//        Category category = new Category();
//        category.setName("Android");
//        List<Category> categories = Arrays.asList(category);
//        given(categoryService.findAll()).willReturn(categories);
//    }
//
//    @DisplayName("find all return status OK")
//    @Test
//    public void findAll_whenGetCategories_thenReturnJsonArray()
//            throws Exception {
//        mvc.perform(get("/categories")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//}
