package com.example.demo.controllers;

import com.example.demo.dtos.auth.AuthenticateRequest;
import com.example.demo.dtos.auth.AuthenticateResponse;
import com.example.demo.dtos.auth.RegisterRequest;
import com.example.demo.dtos.auth.RegisterResponse;
import com.example.demo.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(this.authService.register(registerRequest));
    }
    @PostMapping("authenticate")
    public ResponseEntity<AuthenticateResponse> register(@RequestBody AuthenticateRequest registerRequest){
        return ResponseEntity.ok(this.authService.authenticate(registerRequest));
    }
}
