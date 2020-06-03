package com.example.spring.rabbit;


import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitMqTest {

    @Autowired
    private ConnectionFactory factory;

    @Test
    public void testGetConn() {
        Connection connection = factory.createConnection();
        System.out.println(connection);
    }
}
