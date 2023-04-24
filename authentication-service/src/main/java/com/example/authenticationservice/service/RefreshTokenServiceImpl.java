package com.example.authenticationservice.service;

import com.example.authenticationservice.dto.AuthenticationResponse;
import com.example.authenticationservice.dto.JwtParseResponseDto;
import com.example.authenticationservice.dto.TokenDto;
import com.example.authenticationservice.entity.User;
import com.example.authenticationservice.entity.UserRole;
import com.example.authenticationservice.entity.RefreshToken;
import com.example.authenticationservice.exception.TokenRefreshException;
import com.example.authenticationservice.repository.RefreshTokenRepository;
import com.example.authenticationservice.repository.UserRepository;
import com.example.authenticationservice.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;


@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private int refreshTokenDurationMs;

    @Value("${secret_key}")
    private String SECRET_KEY;


    @Autowired
    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository, JwtUtils jwtUtils) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }


    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


    @Override
    public void verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            throw new TokenRefreshException(token.getToken(), "Expired token. Please issue a new request");
        }
    }


    @Override
    public void deleteById(int id) {
        refreshTokenRepository.deleteById(id);
    }


    @Override
    public void increaseCount(RefreshToken refreshToken) {
        refreshToken.incrementRefreshCount();
        refreshTokenRepository.save(refreshToken);
    }


    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();

        var refreshTokens = jwtUtils.generateRefreshToken(user);

        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(refreshTokens);
        refreshToken.setRefreshCount(0);
        refreshToken.setCreatedAt(Instant.now());
        refreshToken.setUpdatedAt(Instant.now());

        refreshTokenRepository.save(refreshToken);

        return refreshToken;
    }


    public void updateRefreshToken(String refreshToken, String newRefreshToken) {

        RefreshToken refreshToken1 = refreshTokenRepository.findByToken(refreshToken);

        refreshToken1.setToken(newRefreshToken);

        refreshTokenRepository.save(refreshToken1);
    }


    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userName;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userName = jwtUtils.extractUsername(refreshToken);
        if (userName != null) {
            var user = this.userRepository.findByPhoneNumber(userName);
            if (jwtUtils.isTokenValid(refreshToken, user)) {
                var accessToken = jwtUtils.generateToken(user);
                var refreshToken1 = jwtUtils.generateRefreshToken(user);

                updateRefreshToken(refreshToken, refreshToken1);

                AuthenticationResponse authResponse = new AuthenticationResponse(accessToken, refreshToken);

                authResponse.setToken(accessToken);
                authResponse.setRefreshToken(refreshToken);

                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public TokenDto validateToken(String token) throws Exception {

        RefreshToken token1 = refreshTokenRepository.findByToken(token);

        if(token1 == null) {
            throw new Exception("User not found");
        }

        TokenDto tokenDto = new TokenDto();

        if(token1.getUserDevice().getUser().getUserRole().equals(UserRole.USER)) {

            tokenDto.setId(token1.getId());
            tokenDto.setToken(token);
        }

        return tokenDto;
    }


    public JwtParseResponseDto parseJwt(String token) {
        Objects.requireNonNull(token);

        Claims claims = jwtUtils.extractAllClaims(token);

        String username = claims.getSubject();

        List<String> authorities = claims.get("role", List.class);

        return new JwtParseResponseDto(username, authorities);
    }
}
