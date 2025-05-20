package com.example.biliBuddy.user.controller;

import com.example.biliBuddy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bilibuddy")
@RequiredArgsConstructor

public class UserController {
    private final UserRepository userRepository;

//    @PostMapping
}
