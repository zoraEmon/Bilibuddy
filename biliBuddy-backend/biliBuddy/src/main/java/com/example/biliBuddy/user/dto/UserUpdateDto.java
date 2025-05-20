package com.example.biliBuddy.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateDto(
        @NotBlank(message = "Username must not be blank.")
        @Size(min = 8,message = "Username must contain at least 8 characters.")
        String username,
        String profilePicture
) {
}
