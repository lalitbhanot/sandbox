package com.lalit.consumer;

import com.lalit.model.CustomMessageRequest;
import com.lalit.model.CustomMessageRequest2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "lalitBhanotTopic", groupId = "lalitBhanotGroup",
            containerFactory = "kafkaListenerContainerFactory")
//    working part 1  - CustomKafkaConfig
//    public void listen(CustomMessageRequest message) {
//        System.out.println("Received Message: " + message);
//    }

    public void listen(CustomMessageRequest2 message) {
        System.out.println("Received Message : " + message.toString());
    }
}
