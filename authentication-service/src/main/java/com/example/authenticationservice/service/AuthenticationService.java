package com.example.authenticationservice.service;


import com.example.authenticationservice.dto.*;
import com.example.authenticationservice.entity.User;
import com.example.authenticationservice.entity.UserDevice;
import com.example.authenticationservice.entity.UserRole;
import com.example.authenticationservice.entity.RefreshToken;
import com.example.authenticationservice.exception.UserLoginException;
import com.example.authenticationservice.repository.RefreshTokenRepository;
import com.example.authenticationservice.repository.UserRepository;
import com.example.authenticationservice.utils.JwtUtils;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;


@Service
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    private final RefreshTokenServiceImpl refreshTokenService;

    private final UserDeviceServiceImpl userDeviceService;

    private final RefreshTokenRepository refreshTokenRepository;


    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils,
                                 AuthenticationManager authenticationManager, RefreshTokenServiceImpl refreshTokenService,
                                 UserDeviceServiceImpl userDeviceService, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
        this.userDeviceService = userDeviceService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public AuthenticationResponse registrationUser(RegistrationRequest registerRequest) {

        User user = new User();


        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setUserRole(UserRole.USER);
        user.setCreatedAt(Timestamp.from(Instant.now()));
        user.setIsAccountExpired(Boolean.TRUE);
        user.setActive(Boolean.TRUE);
        user.setIsAccountLocked(Boolean.TRUE);
        user.setEnabled(Boolean.TRUE);
        user.setIsTwilioVerified(Boolean.FALSE);

        userRepository.save(user);

        var jwtToken = jwtUtils.generateToken(user);
        var refreshToken = jwtUtils.generateRefreshToken(user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(
                jwtToken,
                refreshToken
        );

        authenticationResponse.setToken(jwtToken);
        authenticationResponse.setRefreshToken(refreshToken);

        return authenticationResponse;
    }


    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest) {

        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));

        User user = (User) authentication.getPrincipal();

        AuthenticationResponse authenticationResponse =
                createAndPersistRefreshTokenForDevice(authentication, authenticationRequest)
                        .map(RefreshToken::getToken)
                        .map(refreshToken -> {
                            String jwtToken = jwtUtils.generateToken(user);
                            String refreshToken2 = jwtUtils.generateToken(user);

                            AuthenticationResponse authenticationResponse1 = new AuthenticationResponse(jwtToken, refreshToken2);

                            authenticationResponse1.setToken(jwtToken);
                            authenticationResponse1.setRefreshToken(refreshToken2);

                            return authenticationResponse1;
                        }).orElseThrow(() -> new UserLoginException("Couldn't create refresh token for: " +
                                "[" + authenticationRequest + "]"));

        return authenticationResponse;
    }


    public Optional<RefreshToken> createAndPersistRefreshTokenForDevice(Authentication authentication,
                                                                        AuthenticationRequest loginRequest) {
        User currentUser = (User) authentication.getPrincipal();
        String deviceId = loginRequest.getDeviceInfo().getDeviceId();
        userDeviceService.findDeviceByUserId(currentUser.getId(), deviceId)
                .map(UserDevice::getRefreshToken)
                .map(RefreshToken::getId)
                .ifPresent(refreshTokenService::deleteById);

        UserDevice userDevice = userDeviceService.createUserDevice(loginRequest.getDeviceInfo());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(currentUser);
        userDevice.setUser(currentUser);
        userDevice.setRefreshToken(refreshToken);
        refreshToken.setUserDevice(userDevice);
        refreshToken = refreshTokenRepository.save(refreshToken);
        return Optional.ofNullable(refreshToken);
    }


    public String changePassword(PasswordRequest passwordRequest, int id) {

        User user = userRepository.getById(id);

        user.setPassword(passwordRequest.getPassword());

        userRepository.save(user);

        return "Change Password";
    }
}
