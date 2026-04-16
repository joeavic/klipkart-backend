package com.nixcraft.User.kafka.producer;

import com.nixcraft.User.kafka.event.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducer
{
    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    private static final String TOPIC = "employee-topic";
    public void publishEmployeeCreated(UserEvent event)
    {
        kafkaTemplate.send(TOPIC, event.getId().toString(), event);
    }
}
