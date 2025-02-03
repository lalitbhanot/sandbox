package com.lalit.controller;

import com.lalit.model.CustomMessageRequest;
import com.lalit.model.CustomMessageRequest2;
import com.lalit.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
@RestController
@RequestMapping("/api/kafka")
public class KafkaController {
    @Autowired
    private KafkaProducer kafkaProducer;
// working part 1 code
//    @PostMapping("/send")
//    public String sendMessage(@RequestBody CustomMessageRequest request) {
//        kafkaProducer.sendMessage(request);
//        return "✅ Message sent successfully!";
//
//    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody CustomMessageRequest2 request2) {
        request2.setCreatedDate(LocalDateTime.now());
        kafkaProducer.sendMessage(request2);
        return "✅ Message sent successfully!";

    }
}
