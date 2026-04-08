package com.app.quantitymeasurement.dto;

public class AuthResponse {

    private String token;

    // ✅ Required for Jackson
    public AuthResponse() {
    }

    // ✅ Used when returning token
    public AuthResponse(String token) {
        this.token = token;
    }

    // getter
    public String getToken() {
        return token;
    }

    // setter
    public void setToken(String token) {
        this.token = token;
    }
}