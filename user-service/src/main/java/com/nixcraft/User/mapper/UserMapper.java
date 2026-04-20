package com.nixcraft.User.mapper;

import com.nixcraft.User.dto.UserRequestDTO;
import com.nixcraft.User.dto.UserResponseDTO;
import com.nixcraft.User.entity.User;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto){
        User user = new User();
        user.setName(dto.getName());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserResponseDTO toUserResponseDTO(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .password(user.getPassword())
                .build();
    }
}
