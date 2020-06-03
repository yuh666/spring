package com.example.spring.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:rabbitmq.properties")
public class RabbitMQConfig {

    @Autowired
    private Environment env;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setVirtualHost(env.getProperty("rabbitmq.vhost"));
        factory.setHost(env.getProperty("rabbitmq.host"));
        factory.setPort(Integer.parseInt(env.getProperty("rabbitmq.port")));
        factory.setUsername(env.getProperty("rabbitmq.username"));
        factory.setPassword(env.getProperty("rabbitmq.password"));
        factory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        return factory;
    }

}
