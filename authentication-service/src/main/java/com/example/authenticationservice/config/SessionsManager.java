package com.example.authenticationservice.config;

public interface SessionsManager {

    public void deleteSessionExceptCurrentByUser(String username);
}
