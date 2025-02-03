package com.lalit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lalit.model.CustomMessageRequest;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class CustomDeserializer implements Deserializer<CustomMessageRequest> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public CustomMessageRequest deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, CustomMessageRequest.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to CustomMessageRequest", e);
        }
    }
}