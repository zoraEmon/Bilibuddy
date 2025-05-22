package com.example.biliBuddy.user.service;

import com.example.biliBuddy.user.dto.UserCreateDto;
import com.example.biliBuddy.user.dto.UserResponseDto;
import com.example.biliBuddy.user.dto.UserUpdateDto;
import com.example.biliBuddy.user.model.User;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserCreateDto userCreateDto);
    UserResponseDto updateUser(UserUpdateDto userUpdateDto, String userId);
    void deleteUser(String userId);
    List<UserResponseDto> getUsers();

    User loadByEmail(String email); //Gets user by email.
}
