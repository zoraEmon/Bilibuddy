package com.example.biliBuddy.auth.controller;

import com.example.biliBuddy.common.exception.ResourceNotFoundException;
import com.example.biliBuddy.common.util.UserMapper;
import com.example.biliBuddy.user.dto.UserResponseDto;
import com.example.biliBuddy.user.model.User;
import com.example.biliBuddy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bilibuddy/api/oauth2")
public class OAuth2Controller {
    private final UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<?> getOAuth2User(@AuthenticationPrincipal OAuth2User oauth2User) {
        return ResponseEntity.ok(oauth2User.getAttributes());
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal OAuth2User oauth2User) {
        String email = oauth2User.getAttribute("email");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        UserResponseDto userResponseDto = UserMapper.toResponseDto(user);
        return ResponseEntity.ok(userResponseDto);
    }


}
