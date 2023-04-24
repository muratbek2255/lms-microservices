package com.example.authenticationservice.repository;

import com.example.authenticationservice.entity.UserDevice;
import com.example.authenticationservice.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDeviceRepository extends JpaRepository<UserDevice, Integer> {

    Optional<UserDevice> findById(int id);

    Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken);

    Optional<UserDevice> findByUserIdAndDeviceId(Integer userId, String userDeviceId);
}
