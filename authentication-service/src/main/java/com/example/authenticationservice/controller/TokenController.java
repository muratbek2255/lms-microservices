package com.example.authenticationservice.controller;


import com.example.authenticationservice.dto.ErrorDto;
import com.example.authenticationservice.dto.JwtParseRequestDto;
import com.example.authenticationservice.dto.JwtParseResponseDto;
import com.example.authenticationservice.dto.TokenDto;
import com.example.authenticationservice.service.RefreshTokenServiceImpl;
import com.example.authenticationservice.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/auth/token")
@Slf4j
public class TokenController {

    private final JwtUtils jwtUtils;

    private final RefreshTokenServiceImpl tokenService;

    @Autowired
    public TokenController(JwtUtils jwtUtils, RefreshTokenServiceImpl tokenService) {
        this.jwtUtils = jwtUtils;
        this.tokenService = tokenService;
    }


    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        tokenService.refreshToken(request, response);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validateToken(@RequestParam("token") String token) throws Exception {

        return ResponseEntity.status(201).body(tokenService.validateToken(token));
    }

    @PostMapping("/parse")
    public ResponseEntity<?> getSomeSensitiveData(@RequestBody JwtParseRequestDto requestDto) {
        try {
            JwtParseResponseDto jwtParseResponseDto = tokenService.parseJwt(requestDto.getToken());
            return new ResponseEntity<>(jwtParseResponseDto, HttpStatus.OK);

        } catch (Exception ex) {
            log.error("JWT parsing error: {}, token: {}", ex.getLocalizedMessage(), requestDto);
            ex.printStackTrace();

            return new ResponseEntity<>(new ErrorDto(ex.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
}
