package com.example.biliBuddy.common.util;

import com.example.biliBuddy.user.dto.UserCreateDto;
import com.example.biliBuddy.user.dto.UserResponseDto;
import com.example.biliBuddy.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public static UserResponseDto toResponseDto(User user) {
        List<String> roles = user.getRoles()
                .stream()
                .map(Enum::toString)
                .collect(Collectors.toList());


        return new UserResponseDto(
          user.getUserId(),
          user.getUsername(),
          user.getProfilePicture(),
          user.getEmail(),
          roles,
          user.getVerificationStatus().toString()
        );
    }

    public static User toEntity(UserCreateDto userCreateDto) {
        User user = new User();
        user.setUsername(userCreateDto.username());
        user.setEmail(userCreateDto.username());
        user.setPassword(userCreateDto.password());

        return user;
    }
}
