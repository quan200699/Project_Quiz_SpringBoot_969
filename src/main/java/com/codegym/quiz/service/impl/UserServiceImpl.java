package com.codegym.quiz.service.impl;

import com.codegym.quiz.model.StudentClass;
import com.codegym.quiz.model.User;
import com.codegym.quiz.model.UserPrinciple;
import com.codegym.quiz.repository.UserRepository;
import com.codegym.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        if (this.checkLogin(user)) {
            return UserPrinciple.build(user);
        }
        boolean enable = false;
        boolean accountNonExpired = false;
        boolean credentialsNonExpired = false;
        boolean accountNonLocked = false;
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), enable, accountNonExpired, credentialsNonExpired,
                accountNonLocked, null);
    }


    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean checkLogin(User user) {
        Iterable<User> users = this.findAll();
        boolean isCorrectUser = false;
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user.getUsername())
                    && user.getPassword().equals(currentUser.getPassword()) &&
                    currentUser.isEnabled()) {
                isCorrectUser = true;
            }
        }
        return isCorrectUser;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isCorrectConfirmPassword(User user) {
        boolean isCorrectConfirmPassword = false;
        if (user.getPassword().equals(user.getConfirmPassword())) {
            isCorrectConfirmPassword = true;
        }
        return isCorrectConfirmPassword;
    }

    @Override
    public Iterable<User> findAllByStudentClass(StudentClass studentClass) {
        return userRepository.findAllByStudentClass(studentClass);
    }
}
