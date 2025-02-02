package com.lalit.producer;

import com.lalit.model.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service

public class SimpleKafkaProducer {

    private final KafkaTemplate<String, User> kafkaTemplate;

    public SimpleKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User user) {
        System.out.println("Producing message: {}"+ user);
        kafkaTemplate.send("user-topic", user.getId(), user);
    }
}
