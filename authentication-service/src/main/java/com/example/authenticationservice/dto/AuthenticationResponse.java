package com.example.authenticationservice.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {

    String token;

    String refreshToken;

    public AuthenticationResponse(String jwtToken, String refreshToken) {
    }
}