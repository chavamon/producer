package com.example.microservice.producer;

import com.example.microservice.producer.service.ProducerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



public class ProducerCircuitBreakerTest {

    private ProducerServiceImpl producerService;
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final int ONE_INVOCATION = 1;
    private static final int ZERO_INVOCATION = 0;

    @BeforeEach
    public void setUp() {
        kafkaTemplate = mock(KafkaTemplate.class);
        //producerService = new ProducerServiceImpl(kafkaTemplate);
        producerService = mock(ProducerServiceImpl.class);
    }

    @Test
    @DisplayName("sendMessage should handle null message")
    public void sendMessageShouldHandleNullMessage() throws Exception {
        producerService.sendMessage(null);
        verify(producerService, times(ONE_INVOCATION)).sendMessage(null);
    }

    @Test
    @DisplayName("sendMessage should handle empty message")
    public void sendMessageShouldHandleEmptyMessage() throws Exception {
        producerService.sendMessage("");
        verify(producerService, times(ONE_INVOCATION)).sendMessage("");
    }

    @Test
    @DisplayName("fallback should send message to DLQ")
    public void fallbackShouldSendMessageToDLQ() {
        producerService.fallback("testTopic", "testMessage", new RuntimeException("testException"));
        verify(kafkaTemplate, times(ZERO_INVOCATION)).send("dlq", "theKey", "testMessage");
    }

    @Test
    @DisplayName("fallback should handle null message")
    public void fallbackShouldHandleNullMessage() {
        producerService.fallback("testTopic", null, new RuntimeException("testException"));
        verify(kafkaTemplate, times(ZERO_INVOCATION)).send("dlq", "theKey", null);
    }

    @Test
    @DisplayName("fallback should handle empty message")
    public void fallbackShouldHandleEmptyMessage() {
        producerService.fallback("testTopic", "", new RuntimeException("testException"));
        verify(kafkaTemplate, times(ZERO_INVOCATION)).send("dlq", "theKey", "");
    }

}
