package com.codegym.quiz.repository;

import com.codegym.quiz.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void init() {
        Category category = new Category();
        category.setName("Java");
        entityManager.persist(category);
        entityManager.flush();
    }

    @DisplayName("findAll can return list (is not null)")
    @Test
    public void whenFindAll_thenReturnListNotNull() {
        assertThat(categoryRepository.findAll()).isNotNull();
    }

    @DisplayName("findAll can return a list has 1 element")
    @Test
    public void whenFindAll_thenReturnListHasOneElement() {
        List<Category> categories = categoryRepository.findAll();
        assertThat(categories).hasSize(1);
    }

    @DisplayName("findByName can return a category has name Java not null")
    @Test
    public void whenFindByName_thenReturnACategoryNotNull() {
        assertThat(categoryRepository.findByName("Java")).isNotNull();
    }
}
