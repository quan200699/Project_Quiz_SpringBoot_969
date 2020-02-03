package com.codegym.quiz.service;

import com.codegym.quiz.model.Category;
import com.codegym.quiz.repository.CategoryRepository;
import com.codegym.quiz.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

public class CategoryServiceTest {
    private CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
    private CategoryService categoryService = new CategoryServiceImpl(categoryRepository);

    @BeforeEach
    void init() {
        Category category = new Category();
        category.setName("Android");
        doReturn(Optional.of(category)).when(categoryRepository).findById(1L);
        List<Category> categories = Arrays.asList(category);
        doReturn(categories).when(categoryRepository).findAll();
    }

    @DisplayName("findAll can return list (is not null)")
    @Test
    void whenFindAllNotNull() {
        assertThat(categoryService.findAll()).isNotNull();
    }

    @DisplayName("findAll can return a list has 1 element")
    @Test
    public void whenFindAllReturnOneElement() {
        List<Category> actualCategories = (List<Category>) categoryService.findAll();
        assertThat(actualCategories).hasSize(1);
    }

    @DisplayName("findById return a category has name Android")
    @Test
    public void whenFindByIdReturnCategoryNameAndroid() {
        Optional<Category> actualCategory = categoryService.findById(1L);
        assertThat(actualCategory.get().getName()).isEqualTo("Android");
    }
}
