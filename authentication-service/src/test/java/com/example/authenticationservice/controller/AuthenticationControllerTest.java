package com.example.authenticationservice.controller;

import com.example.authenticationservice.dto.AuthenticationRequest;
import com.example.authenticationservice.dto.AuthenticationResponse;
import com.example.authenticationservice.dto.PasswordRequest;
import com.example.authenticationservice.dto.RegistrationRequest;
import com.example.authenticationservice.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {


    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private RegistrationRequest request;

    @Mock
    private AuthenticationRequest authenticationRequest;

    @Mock
    private PasswordRequest passwordRequest;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    public void setUp() {
        authenticationRequest = new AuthenticationRequest();
        request = new RegistrationRequest();
        passwordRequest = new PasswordRequest();
    }


    @Test
    public void testRegistrationController() {

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        Mockito.when(authenticationService.registrationUser(request)).thenReturn(authenticationResponse);

        ResponseEntity<AuthenticationResponse> response = authenticationController.register(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testAuthenticationRequest() {

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        Mockito.when(authenticationService.authentication(authenticationRequest)).thenReturn(authenticationResponse);

        ResponseEntity<AuthenticationResponse> response = authenticationController.authenticate(authenticationRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testChangePasswordRequest() {

        int id = 1;

        Mockito.when(authenticationService.changePassword(passwordRequest, 1)).thenReturn("success");

        ResponseEntity<String> response = authenticationController.changePassword(passwordRequest, id);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}