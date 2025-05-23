package com.example.biliBuddy.auth.service;

import com.example.biliBuddy.auth.dto.AuthRequestDto;
import com.example.biliBuddy.auth.dto.AuthResponseDto;
import com.example.biliBuddy.common.exception.ResourceNotFoundException;
import com.example.biliBuddy.common.util.JwtService;
import com.example.biliBuddy.user.model.User;
import com.example.biliBuddy.user.model.enums.Role;
import com.example.biliBuddy.user.model.enums.VerificationStatus;
import com.example.biliBuddy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImp implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDto register(AuthRequestDto requestDto) {
        if(userRepository.existsByUsername(requestDto.username())
                || userRepository.existsByEmail(requestDto.email())) {
            throw new RuntimeException("Username or email address already in use");
        }

        User user = User.builder()
                .username(requestDto.username())
                .email(requestDto.email())
                .password(passwordEncoder.encode(requestDto.password()))
                .roles(Set.of(Role.USER))
                .verificationStatus(VerificationStatus.PENDING)
                .build();

        userRepository.save(user);

        return this.toAuthResponseDto(user.getUsername());
    }

    @Override
    public AuthResponseDto login(AuthRequestDto requestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.email(),
                        requestDto.password()
                )
        );

        User user = userRepository.findByEmail(requestDto.email()).
                orElseThrow(() -> new ResourceNotFoundException("User not found."));

       return this.toAuthResponseDto(user.getEmail());
    }

    //For mapping access, refresh token and expiration date to authResponseDto
    private AuthResponseDto toAuthResponseDto(String email) {
        String accessToken = jwtService.generateToken(email, Collections.emptyMap());
        String refreshToken = jwtService.generateRefreshToken(email);
        long expirationTime = jwtService.getExpirationTime();

        return new AuthResponseDto(accessToken, refreshToken, expirationTime);
    }
}
