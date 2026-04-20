package com.nixcraft.User.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserResponseDTO {
    private final Long id;
    private final String email;
    private final String password;
    private final String phone;
}
