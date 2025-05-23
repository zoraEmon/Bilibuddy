package com.example.biliBuddy.auth.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        long expireAt //Millisecs in Unix (Timestamp)
) {
}
