package com.example.auth.service;

import com.example.auth.client.user.UserClient;
import com.example.auth.client.user.UserClientImpl;
import com.example.auth.client.user.dto.UserResponseDto;
import com.example.auth.dto.LoginRequest;
import com.example.auth.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserClient userClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserClient userClient,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userClient = userClient;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String login(LoginRequest request) {

        UserResponseDto user = userClient.getUserByEmail(request.getEmail());

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}
