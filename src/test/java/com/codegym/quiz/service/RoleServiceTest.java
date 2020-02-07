package com.codegym.quiz.service;

import com.codegym.quiz.model.Role;
import com.codegym.quiz.repository.RoleRepository;
import com.codegym.quiz.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

public class RoleServiceTest {
    private RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
    private RoleService roleService = new RoleServiceImpl(roleRepository);

    @BeforeEach
    void init() {
        Role role = new Role();
        role.setName("ROLE_USER");
        doReturn(role).when(roleRepository).findRoleByName("ROLE_USER");
        List<Role> roles = Arrays.asList(role);
        doReturn(roles).when(roleRepository).findAll();
    }

    @DisplayName("findAll can return a list not null")
    @Test
    public void whenFindAll_thenReturnAListNotNull() {
        List<Role> roles = (List<Role>) roleService.findAll();
        assertThat(roles).isNotNull();
    }
}