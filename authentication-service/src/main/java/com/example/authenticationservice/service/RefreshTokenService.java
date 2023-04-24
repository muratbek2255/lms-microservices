package com.example.authenticationservice.service;

import com.example.authenticationservice.entity.RefreshToken;

public interface RefreshTokenService {

    public RefreshToken findByToken(String token);

    public void verifyExpiration(RefreshToken token);

    public void deleteById(int id);

    public void increaseCount(RefreshToken refreshToken);
}
