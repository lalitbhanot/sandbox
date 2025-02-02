package com.lalit.controller;

import com.lalit.model.User;
import com.lalit.producer.SimpleKafkaProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class SimpleUserController {
    private final SimpleKafkaProducer producer;
// Constrcutor Injection of KafkaProducer in SimpleUSerController
    public SimpleUserController(SimpleKafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        producer.sendMessage(user);
        return "User message sent to Kafka topic";
    }
}