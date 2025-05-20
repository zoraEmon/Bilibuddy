package com.example.biliBuddy.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreateDto(
    @Size(min = 8, message = "Username must be at least 8 characters.")
    String username,

    @Email(message = "Invalid email format.")
    @NotBlank(message = "Email is required")
    String email,

    @Size(min = 12, message = "Password must be at least 12 characters.")
    @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least 1 uppercase letter.")
    @Pattern(regexp = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*", message = "Password must contain at least 1 special character.")
    @Pattern(regexp = "\\S+", message = "Password must not contain any spaces.")
    String password

) {
}
