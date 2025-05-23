package com.example.biliBuddy.auth.dto;

public record AuthResponseDto(
        String accessToken,
        String refreshToken,
        long expireAt
) {
}
