package com.example.biliBuddy.user.model.enums;

import lombok.Getter;

public enum Role {
    USER("User"),
    SELLER("Seller"),
    MODERATOR("Moderator");

    @Getter
    private final String name;

    Role(String role) {
        this.name = role;
    }

    @Override
    public String toString() {
        return name;
    }
}
