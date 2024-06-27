package com.example.microservice.producer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.UUID;

@Service
public class ProducerServiceImpl implements ProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String TOPIC_NAME;

    public ProducerServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String message) throws UnknownHostException {
        String MESSAGE_KEY = UUID.randomUUID().toString();

        kafkaTemplate.send(
                TOPIC_NAME,
                MESSAGE_KEY,
                message);
    }
}
