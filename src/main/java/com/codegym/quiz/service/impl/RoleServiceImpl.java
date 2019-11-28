package com.codegym.quiz.service.impl;

import com.codegym.quiz.model.Role;
import com.codegym.quiz.repository.RoleRepository;
import com.codegym.quiz.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findRoleByName(roleName);
    }
}
