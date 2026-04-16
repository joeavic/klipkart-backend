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
        return user;
    }

    public static UserResponseDTO toEmployeeResponseDTO(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
}
