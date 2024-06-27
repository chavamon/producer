package com.example.microservice.producer.controller;

import com.example.microservice.producer.service.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/producer")
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/send")
    public ResponseEntity<Mono<Object>> sendMessage(@RequestBody String message) throws Exception {
        producerService.sendMessage(message);
        return ResponseEntity.ok().body(Mono.empty());
    }


}
