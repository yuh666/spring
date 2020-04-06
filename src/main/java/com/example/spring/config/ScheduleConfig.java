package com.example.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleConfig {

    @Scheduled(cron = "10,20,30,40,50 * * * * ?")
    public void refresh1() {
        System.out.println("refresh1");
    }
}
