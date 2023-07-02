package com.example.demo.dtos.auth;

import lombok.Builder;

@Builder
public record RegisterResponse(
        String token
) {
}
