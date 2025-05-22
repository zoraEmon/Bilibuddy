package com.example.biliBuddy.user.service;

import com.example.biliBuddy.common.util.UserMapper;
import com.example.biliBuddy.user.dto.UserCreateDto;
import com.example.biliBuddy.user.dto.UserResponseDto;
import com.example.biliBuddy.user.dto.UserUpdateDto;
import com.example.biliBuddy.user.model.User;
import com.example.biliBuddy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //Mthod for creating a user.
    @Override
    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        User user = UserMapper.toEntity(userCreateDto);
        user.setPassword(passwordEncoder.encode(userCreateDto.password()));
        userRepository.save(user);

        return UserMapper.toResponseDto(user);
    }

    //Method for updating a particular user.
    @Override
    public UserResponseDto updateUser(UserUpdateDto userUpdateDto, String userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("No such user"));

        String newUsername = userUpdateDto.username();

        if (user.getUsername().equals(newUsername)) {
            throw new RuntimeException("This is your current username.");
        } else if (userRepository.existsByUsername(newUsername)) {
            throw new RuntimeException("This username already exists.");
        }

        user.setUsername(newUsername);
        user.setProfilePicture(userUpdateDto.profilePicture());

        userRepository.save(user);

        return UserMapper.toResponseDto(user);
    }

    @Override //For user deletion
    public void deleteUser(String userId) {
        User user = userRepository.findByUserIdAndDeletedFalse(userId)
                .orElseThrow(() -> new RuntimeException("No such user."));

        user.setDeleted(true);
        userRepository.save(user);
    }

    @Override //ofc, for getting the list of all users.
    public List<UserResponseDto> getUsers() {
        List<User> users = userRepository.findAll();

        List<UserResponseDto> userResponseDtos =
                users.stream()
                        .filter(user -> !user.isDeleted())
                        .map(UserMapper::toResponseDto)
                        .collect(Collectors.toList());

        return userResponseDtos;
    }
}
