package com.example.biliBuddy.user.repository;

import com.example.biliBuddy.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserIdAndDeletedFalse(String userId);
    Optional<User> findByUsernameAndDeletedFalse(String username);
    Optional<User> findByEmailAndDeletedFalse(String email);
}
