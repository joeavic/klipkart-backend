package com.example.auth.client.user;

import com.example.auth.client.user.dto.UserResponseDto;

public interface UserClient {
    UserResponseDto getUserByEmail(String email);
}
