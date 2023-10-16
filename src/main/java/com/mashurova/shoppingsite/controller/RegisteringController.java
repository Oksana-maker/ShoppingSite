package com.mashurova.shoppingsite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisteringController {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsManager userDetailsManager;

    public record CreateUserCommand(String username, String password){}

    @GetMapping
    public String index() {
        return "register";
    }

    @PostMapping
    public String register(CreateUserCommand command) {
        userDetailsManager.createUser(User.withUsername(command.username())
                .password(passwordEncoder.encode(command.password()))
                .roles("USER")
                .build());
        return "redirect:/singin";
    }
}
