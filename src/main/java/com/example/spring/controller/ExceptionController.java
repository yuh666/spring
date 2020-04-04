package com.example.spring.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ex")
public class ExceptionController implements ErrorController {

    @RequestMapping("/throw")
    public Object demo1() {
        throw new RuntimeException();
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
