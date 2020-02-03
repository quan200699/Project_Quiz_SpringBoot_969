package com.codegym.quiz.repository;

import com.codegym.quiz.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
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

}
