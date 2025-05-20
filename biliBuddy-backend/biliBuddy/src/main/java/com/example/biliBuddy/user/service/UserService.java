package com.example.biliBuddy.user.service;

import com.example.biliBuddy.user.dto.UserCreateDto;
import com.example.biliBuddy.user.dto.UserResponseDto;
import com.example.biliBuddy.user.dto.UserUpdateDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserCreateDto userCreateDto);
    UserResponseDto updateUser(UserUpdateDto userUpdateDto, String userId);
    void deleteUser(String userId);
    List<UserResponseDto> getUsers();
}
