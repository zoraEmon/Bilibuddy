package com.example.biliBuddy.user.controller;

import com.example.biliBuddy.user.dto.UserCreateDto;
import com.example.biliBuddy.user.dto.UserResponseDto;
import com.example.biliBuddy.user.dto.UserUpdateDto;
import com.example.biliBuddy.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bilibuddy/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping //Sends a request in order to create a user.
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        UserResponseDto userResponseDto = userService.createUser(userCreateDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping("/{id}") //User deletion request.
    public ResponseEntity<String> deleteUser(@RequestParam String userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Deletion unsuccessful. An error occurred: " + e.getMessage());

        }
    }

    @PutMapping("/{id}") //Request for user update.
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto,
                                                      @PathVariable String userId) {
        UserResponseDto userResponseDto = userService.updateUser(userUpdateDto, userId);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping//Request for gettin all users.
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> userResponseDtos = userService.getUsers();
        return ResponseEntity.ok(userResponseDtos);
    }
}
