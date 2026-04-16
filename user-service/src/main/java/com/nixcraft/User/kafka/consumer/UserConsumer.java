package com.nixcraft.User.kafka.consumer;

import com.nixcraft.User.kafka.event.UserEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



@Service
public class UserConsumer
{

    private static final Logger log = LoggerFactory.getLogger(UserConsumer.class);


    @KafkaListener(topics = "employee-topic", groupId = "employee-group")
    public void consume(UserEvent event)
    {
        log.info("Received Event: " + event.getEventType()
                + " for Employee ID: " + event.getId());
    }
}
