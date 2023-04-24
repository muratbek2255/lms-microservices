package com.example.authenticationservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse {

    final String data;
    final Boolean success;
    final String timestamp;
    final String cause;
    final String path;

    public ApiResponse(Boolean success, String data, String cause, String path) {
        this.timestamp = Instant.now().toString();
        this.data = data;
        this.success = success;
        this.cause = cause;
        this.path = path;
    }

    public ApiResponse(Boolean success, String data) {
        this.timestamp = Instant.now().toString();
        this.data = data;
        this.success = success;
        this.cause = null;
        this.path = null;
    }

    public String getData() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getCause() {
        return cause;
    }

    public String getPath() {
        return path;
    }
}
