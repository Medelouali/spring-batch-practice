package com.example.demo.dtos.auth;

public record AuthenticateRequest(
        String email,
        String password
) {
}
