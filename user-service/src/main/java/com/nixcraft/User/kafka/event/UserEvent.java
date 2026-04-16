package com.nixcraft.User.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent
{
    private String eventType;
    private Long Id;
    private String name;
    private String email;
    private String phone;
}
