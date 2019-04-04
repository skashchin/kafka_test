package com.ss.test.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.test.producer.model.SsHttpMessage;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class JsonSerializer implements Serializer<SsHttpMessage> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public byte[] serialize(String topic, SsHttpMessage ssHttpMessage) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            retVal = objectMapper.writeValueAsString(ssHttpMessage).getBytes();
        } catch (Exception exception) {
            System.out.println("Error in serializing object" + ssHttpMessage);
        }

        return retVal;
    }

    @Override
    public void close() {

    }
}
