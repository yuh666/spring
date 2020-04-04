package com.example.spring.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;


@ControllerAdvice
public class ControllerHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        return ResponseEntity.ok("ok");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleErrStatus() {
        return ResponseEntity.ok("not found");
    }
}
