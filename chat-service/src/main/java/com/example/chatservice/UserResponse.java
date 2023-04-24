package com.example.chatservice;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private final String name;
    private final boolean online;

    public UserResponse(User user, boolean online) {
        this.name = user.getPhoneNumber();
        this.online = online;
    }
}