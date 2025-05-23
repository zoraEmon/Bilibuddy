package com.example.biliBuddy.auth.controller;

import com.example.biliBuddy.auth.dto.AuthRequestDto;
import com.example.biliBuddy.auth.dto.AuthResponseDto;
import com.example.biliBuddy.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bilibuddy/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    //Register a new user.
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody @Valid AuthRequestDto authRequestDto) {
        AuthResponseDto authResponseDto = authService.register(authRequestDto);
        return ResponseEntity.ok(authResponseDto);
    }

    //Ofc. for loggin in.
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid AuthRequestDto authRequestDto) {
        AuthResponseDto authResponseDto = authService.login(authRequestDto);
        return ResponseEntity.ok(authResponseDto);
    }

}
