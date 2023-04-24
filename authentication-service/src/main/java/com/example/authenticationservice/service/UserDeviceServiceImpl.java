package com.example.authenticationservice.service;

import com.example.authenticationservice.dto.DeviceInfo;
import com.example.authenticationservice.entity.UserDevice;
import com.example.authenticationservice.entity.RefreshToken;
import com.example.authenticationservice.exception.TokenRefreshException;
import com.example.authenticationservice.repository.UserDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;


@Service
public class UserDeviceServiceImpl implements UserDeviceService {


    private final UserDeviceRepository userDeviceRepository;

    @Autowired
    public UserDeviceServiceImpl(UserDeviceRepository userDeviceRepository) {
        this.userDeviceRepository = userDeviceRepository;
    }

    @Override
    public Optional<UserDevice> findDeviceByUserId(Integer userId, String deviceId) {
        return userDeviceRepository.findByUserIdAndDeviceId(userId, deviceId);
    }

    @Override
    public Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken) {
        return userDeviceRepository.findByRefreshToken(refreshToken);
    }

    @Override
    public UserDevice createUserDevice(DeviceInfo deviceInfo) {

        UserDevice userDevice = new UserDevice();

        userDevice.setDeviceId(deviceInfo.getDeviceId());
        userDevice.setDeviceType(deviceInfo.getDeviceType());
        userDevice.setIsRefreshActive(true);
        userDevice.setCreatedAt(Instant.now());
        userDevice.setUpdatedAt(Instant.now());

        return userDevice;
    }

    @Override
    public void verifyRefreshAvailability(RefreshToken refreshToken) {
        UserDevice userDevice = findByRefreshToken(refreshToken)
                .orElseThrow(() -> new TokenRefreshException(refreshToken.getToken(),
                        "Устройство для соответствующего токена не найдено. Пожалуйста, войдите снова"));

        if (!userDevice.getIsRefreshActive()) {
            throw new TokenRefreshException(refreshToken.getToken(),
                    "Обновление заблокировано для устройства. Пожалуйста, войдите через другое устройство");
        }
    }
}
