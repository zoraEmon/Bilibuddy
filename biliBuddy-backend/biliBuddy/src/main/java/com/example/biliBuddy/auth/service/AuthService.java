package com.example.biliBuddy.auth.service;

import com.example.biliBuddy.auth.dto.AuthRequestDto;
import com.example.biliBuddy.auth.dto.AuthResponseDto;

public interface AuthService {
    AuthResponseDto register(AuthRequestDto authRequestDto);
    AuthResponseDto login(AuthRequestDto authRequestDto);
}
