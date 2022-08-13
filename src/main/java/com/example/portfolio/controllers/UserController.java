package com.example.portfolio.controllers;

import com.example.portfolio.entity.Role;
import com.example.portfolio.entity.UserEntity;
import com.example.portfolio.repository.UserRepository;
import com.example.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "auth/registration";
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping("/registration")
    public String addUser(UserEntity userEntity, Map<String, Object> model) {


        UserEntity userEntity1 = userRepository.findByUsername(userEntity.getUsername());

        if (userEntity1 != null) {
            model.put("message", "User with this username already exists!");
            return "auth/registration";
        }

        userEntity.setActive(true);
        userEntity.setRoles(Collections.singleton(Role.USER));
        userEntity.setPassword(encoder().encode(userEntity.getPassword()));
        userRepository.save(userEntity);

        return "redirect:/login";
    }

    @GetMapping("/user")
    public ResponseEntity getOneUser(@RequestParam Long id) {
        return ResponseEntity.ok(userService.getOne(id));
    }
}
