package com.nixcraft.User.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthService {

    private final WebClient webClient;

    public AuthService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Boolean validateToken(String token) {
        return webClient.post()
                .uri("http://auth-service:8080/auth/validate")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block(); // blocking for now (simple case)
    }
}
