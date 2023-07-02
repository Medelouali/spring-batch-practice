package com.example.demo.dtos.auth;

import lombok.Builder;

@Builder
public record AuthenticateResponse(
        String token
) {
}
