package com.lalit.config;
import com.lalit.model.CustomMessageRequest;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomSerializer implements Serializer<CustomMessageRequest> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, CustomMessageRequest data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing CustomMessageRequest to byte[]", e);
        }
    }
}