package com.example.spring.rabbit;


import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class RabbitMqTest {

    @Autowired
    private RabbitTemplate template;

    @Test
    public void testSimpleQueue() {

        for (int i = 0; i < 5; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("key", i + "");
            Object receive = template.convertSendAndReceive("topicExchange", (i % 2) + "", map);
            System.out.println(receive);
        }
    }
}
