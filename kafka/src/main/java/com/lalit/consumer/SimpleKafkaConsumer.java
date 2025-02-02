package com.lalit.consumer;

import com.lalit.model.User;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service

public class SimpleKafkaConsumer {


    @KafkaListener(topics = "user-topic", groupId = "myGroup")
        public void listen(User user) {
            System.out.println("Received message: {}" + user);
            // Process the message here
        }
}
