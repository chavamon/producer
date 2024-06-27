package com.example.microservice.producer;

import com.example.microservice.producer.service.ProducerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.times;

@SpringBootTest
class ProducerApplicationTests {

	private KafkaTemplate<String, Object> kafkaTemplate;
	private ProducerServiceImpl producerService;

	private static final int ONE_INVOCATION = 1;

	@BeforeEach
	public void setUp() {
		kafkaTemplate = Mockito.mock(KafkaTemplate.class);
		producerService = Mockito.spy(new ProducerServiceImpl(kafkaTemplate));
	}

	@Test
	@DisplayName("sendMessage should complete successfully")
	public void sendMessageShouldCompleteSuccessfully() throws Exception {
		producerService.sendMessage("Message mock");
		Mockito.verify(producerService, times(ONE_INVOCATION)).sendMessage("Message mock");
	}

}
