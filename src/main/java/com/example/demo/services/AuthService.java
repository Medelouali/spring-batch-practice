package com.example.demo.services;

import com.example.demo.config.security.JwtService;
import com.example.demo.dtos.auth.AuthenticateRequest;
import com.example.demo.dtos.auth.AuthenticateResponse;
import com.example.demo.dtos.auth.RegisterRequest;
import com.example.demo.dtos.auth.RegisterResponse;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest registerRequest) {
        User user=User
                .builder()
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(Role.Client)
                .email(registerRequest.email())
                .build();
        this.userRepository.save(user);
        final String jwtToken=this.jwtService.generateToken(user);
        return RegisterResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticateResponse authenticate(AuthenticateRequest registerRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerRequest.email(),
                        registerRequest.password()
                )
        );
        final User user=this.userRepository.findByEmail(registerRequest.email()).orElseThrow();
        final String jwtToken=this.jwtService.generateToken(user);
        return AuthenticateResponse.builder()
                .token(jwtToken)
                .build();
    }
}
