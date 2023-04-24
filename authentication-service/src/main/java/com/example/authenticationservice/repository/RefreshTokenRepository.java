package com.example.authenticationservice.repository;

import com.example.authenticationservice.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findById(int id);

    RefreshToken findByToken(String token);
}
