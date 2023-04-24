package com.example.userservice;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(schema = "auth", name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "password")
    String password;

    @Column(name = "role_user")
    @Enumerated(value = EnumType.STRING)
    UserRole userRole;

    @Column(name = "created_at")
    Timestamp createdAt;

    @Column(name = "credentials_expiry_date")
    LocalDateTime credentialsExpiryDate;

    @Column(name = "is_account_non_expired")
    Boolean isAccountExpired;

    @Column(name = "is_account_non_locked")
    Boolean isAccountLocked;

    @Column(name = "is_active", nullable = false)
    Boolean active;

    @Column(name = "is_enabled")
    Boolean enabled;

    @Column(name = "is_twilio_verified")
    Boolean isTwilioVerified;
}