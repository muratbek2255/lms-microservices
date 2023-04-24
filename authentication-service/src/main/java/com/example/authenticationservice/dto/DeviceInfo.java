package com.example.authenticationservice.dto;


import com.example.authenticationservice.entity.DeviceType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeviceInfo {

    String deviceId;

    DeviceType deviceType;
}
