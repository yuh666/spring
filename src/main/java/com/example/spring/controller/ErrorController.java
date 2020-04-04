package com.example.spring.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ErrorController {


    @RequestMapping("/error")
    public ResponseEntity<Object> error(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(response.getStatus());
    }
}
