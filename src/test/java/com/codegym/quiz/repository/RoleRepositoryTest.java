//package com.codegym.quiz.repository;
//
//import com.codegym.quiz.model.Role;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class RoleRepositoryTest {
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @BeforeEach
//    void init() {
//        Role userRole = new Role();
//        userRole.setName("ROLE_USER");
//        entityManager.persist(userRole);
//        entityManager.flush();
//    }
//
//    @DisplayName("findAll can return a list not null")
//    @Test
//    public void whenFindAll_thenReturnAListNotNull() {
//        assertThat(roleRepository.findAll()).isNotNull();
//    }
//
//    @DisplayName("findByName can return a Role has name ROLE_USER")
//    @Test
//    public void whenFindAll_thenReturnARoleNotNull() {
//        assertThat(roleRepository.findRoleByName("ROLE_USER")).isNotNull();
//    }
//}
