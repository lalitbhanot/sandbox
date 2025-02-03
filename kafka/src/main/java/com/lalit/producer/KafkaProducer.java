package com.lalit.producer;

import com.lalit.model.CustomMessageRequest;
import com.lalit.model.CustomMessageRequest2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final String TOPIC = "lalitBhanotTopic";

//    @Autowired
//    private KafkaTemplate<String, CustomMessageRequest> kafkaTemplate;
//
//    public void sendMessage(CustomMessageRequest message) {
//        kafkaTemplate.send(TOPIC, message);
//        System.out.println("Sent message: " + message);
//    }

    @Autowired
   private KafkaTemplate<String, CustomMessageRequest2> kafkaTemplate;

  public void sendMessage(CustomMessageRequest2 message2) {
       kafkaTemplate.send(TOPIC, message2);
       System.out.println("Sent message: " + message2.toString());
    }
}
