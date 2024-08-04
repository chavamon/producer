package com.example.microservice.producer.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.UnknownHostException;
import java.util.UUID;

@Service
public class ProducerServiceImpl implements ProducerService {

    private static final Logger LOG = LoggerFactory.getLogger(ProducerServiceImpl.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String TOPIC_NAME;

    @Value("${kafka.dlq.topic.name}")
    private String DLQ_TOPIC_NAME = "dlq";

    public ProducerServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @CircuitBreaker(name = "sendMessage", fallbackMethod = "fallback")
    public void sendMessage(String message) throws UnknownHostException {
        String MESSAGE_KEY = UUID.randomUUID().toString();

        Mono.fromRunnable(() -> kafkaTemplate.send(
                TOPIC_NAME,
                MESSAGE_KEY,
                message)).then();
    }

    public void fallback(String topic, String message, Throwable throwable) {
        LOG.error("Producer Fallback method for topic: {}, message: {}, error: {}", topic, message, throwable.getMessage());
        String MESSAGE_KEY = UUID.randomUUID().toString();
        Mono.fromRunnable(() -> kafkaTemplate.send(
                DLQ_TOPIC_NAME,
                MESSAGE_KEY,
                message)).then();
    }

}
