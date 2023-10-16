package com.mashurova.shoppingsite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.context.annotation.Conditional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class EncodingController {
    public final PasswordEncoder passwordEncoder;
    @GetMapping("/encode")
    @ResponseBody
    public String encode(@RequestParam String text) {
        return passwordEncoder.encode(text);
    }
}
