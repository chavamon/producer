package com.example.microservice.producer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.UnknownHostException;

@ControllerAdvice
public class ProducerExceptionHandler {

    @ExceptionHandler(ProducerInternalServerError.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex) {
        return new ResponseEntity<>("500 Error WTF!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<?> unknownHostException(UnknownHostException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
