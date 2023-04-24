package com.example.authenticationservice.controller;


import com.example.authenticationservice.dto.*;
import com.example.authenticationservice.service.AuthenticationService;
import com.example.authenticationservice.service.RefreshTokenServiceImpl;
import com.example.authenticationservice.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest
                                                                   registerRequest) {
        return ResponseEntity.status(201).body(authenticationService.registrationUser(registerRequest));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest
                                                                       authenticationRequest) {
        return ResponseEntity.status(201).body(authenticationService.authentication(authenticationRequest));
    }

    @PostMapping("/changePassword/{id}")
    public ResponseEntity<String> changePassword(PasswordRequest passwordRequest, int id) {

        return ResponseEntity.status(201).body(authenticationService.changePassword(passwordRequest, id));
    }
}