package com.example.auth.client.user;

import com.example.auth.client.user.dto.UserResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UserClientImpl implements UserClient
{

    private final WebClient webClient;

    public UserClientImpl(WebClient webclient)
    {
        this.webClient = webclient;
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        return webClient.get()
                .uri("/users/email/{email}", email)
                .retrieve()
                .bodyToMono(UserResponseDto.class)
                .block();
    }
}
