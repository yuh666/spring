package com.example.spring.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/ex")
@Validated
public class ExceptionController {

    @RequestMapping("/throw")
    public Object demo1(@NotNull(message = "a cannot be null") String a) {
        throw new RuntimeException();
    }

}
