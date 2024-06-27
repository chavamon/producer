package com.example.microservice.producer.service;

import java.net.UnknownHostException;

public interface ProducerService {

    void sendMessage(String message) throws UnknownHostException;

}
