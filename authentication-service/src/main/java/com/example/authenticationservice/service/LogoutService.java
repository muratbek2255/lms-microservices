package com.example.authenticationservice.service;


import com.example.authenticationservice.repository.RefreshTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;


@Service
public class LogoutService implements LogoutHandler {

    private final RefreshTokenRepository tokenRepository;

    private final RefreshTokenServiceImpl tokenService;

    @Autowired
    public LogoutService(RefreshTokenRepository tokenRepository, RefreshTokenServiceImpl tokenService) {
        this.tokenRepository = tokenRepository;
        this.tokenService = tokenService;
    }

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        var storedToken = tokenRepository.findByToken(jwt);
        if (storedToken != null) {
            tokenRepository.deleteById(storedToken.getId());
            SecurityContextHolder.clearContext();
        }
    }
}
