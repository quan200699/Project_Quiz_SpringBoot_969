package com.codegym.quiz.service;

import com.codegym.quiz.model.Role;

public interface RoleService {
    Role findRoleByName(String roleName);

    Iterable<Role> findAll();

    void save(Role role);
}
