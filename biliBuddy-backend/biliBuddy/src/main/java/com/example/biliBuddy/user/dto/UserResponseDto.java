package com.example.biliBuddy.user.dto;

import java.util.List;

public record UserResponseDto(
        String userId,
        String username,
        String profilePicture,
        String email,
        List<String> roles,
        String verificationStatus
) {
}
