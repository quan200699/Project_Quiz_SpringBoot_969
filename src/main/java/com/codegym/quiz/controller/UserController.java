package com.codegym.quiz.controller;

import com.codegym.quiz.model.*;
import com.codegym.quiz.service.RoleService;
import com.codegym.quiz.service.StudentClassService;
import com.codegym.quiz.service.UserService;
import com.codegym.quiz.service.VerificationTokenService;
import com.codegym.quiz.service.impl.EmailService;
import com.codegym.quiz.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.codegym.quiz.model.StaticVariable.*;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private Environment env;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudentClassService studentClassService;

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> showAllUser() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Iterable<User> users = userService.findAll();
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user.getUsername())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        if (!userService.isCorrectConfirmPassword(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Role> roleList = (List<Role>) roleService.findAll();
        if (roleList.isEmpty()) {
            Role roleUser = new Role();
            roleUser.setId(1L);
            roleUser.setName(ROLE_USER);
            Role roleAdmin = new Role();
            roleAdmin.setId(2L);
            roleAdmin.setName(ROLE_ADMIN);
            roleService.save(roleUser);
            Role roleTutor = new Role();
            roleTutor.setId(3L);
            roleTutor.setName(ROLE_TUTOR);
            roleService.save(roleTutor);
        }
        Role role = roleService.findRoleByName(ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
        userService.save(user);
        VerificationToken token = new VerificationToken(user);
        token.setExpiryDate(10);
        verificationTokenService.save(token);
        emailService.sendEmail(user.getEmail(), SUBJECT_REGISTER, TEXT_REGISTER + env.getProperty("confirmAccountLink") + token.getToken());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Void> confirmUserAccount(@RequestParam("token") String verificationToken) {
        VerificationToken token = verificationTokenService.findByToken(verificationToken);
        if (token != null) {
            boolean isExpired = token.isExpired();
            if (!isExpired) {
                User user = userService.findByEmail(token.getUser().getEmail());
                user.setEnabled(true);
                userService.save(user);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(user.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getAvatar(), userDetails.getAuthorities()));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getProfile(@PathVariable Long id) {
        Optional<User> userOptional = this.userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = this.userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(userOptional.get().getId());
        user.setUsername(userOptional.get().getUsername());
        user.setEmail(userOptional.get().getEmail());
        user.setEnabled(userOptional.get().isEnabled());
        user.setPassword(userOptional.get().getPassword());
        user.setRoles(userOptional.get().getRoles());
        user.setConfirmPassword(userOptional.get().getConfirmPassword());
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/new-password", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<User> updatePassword(@RequestParam("token") String token, @RequestBody User user) {
        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        boolean isExpired = verificationToken.isExpired();
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (isExpired) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User currentUser = userService.findByEmail(verificationToken.getUser().getEmail());
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!userService.isCorrectConfirmPassword(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String newPassword = passwordEncoder.encode(user.getPassword());
        String confirmPassword = passwordEncoder.encode(user.getConfirmPassword());
        currentUser.setPassword(newPassword);
        currentUser.setConfirmPassword(confirmPassword);
        userService.save(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @PostMapping("/change-password/{id}")
    public ResponseEntity<User> changePassword(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!userService.isCorrectConfirmPassword(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String oldPassword = user.getOldPassword();
        String newPassword = passwordEncoder.encode(user.getPassword());
        String confirmPassword = passwordEncoder.encode(user.getConfirmPassword());
        if (!passwordEncoder.matches(oldPassword, userOptional.get().getPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setOldPassword(oldPassword);
        user.setPassword(newPassword);
        user.setConfirmPassword(confirmPassword);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/usersByClass")
    public ResponseEntity<Iterable<User>> findAllUserByClass(@RequestParam("studentClass") String name) {
        StudentClass studentClass = studentClassService.findByName(name);
        if (studentClass == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<User> users = userService.findAllByStudentClass(studentClass);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
