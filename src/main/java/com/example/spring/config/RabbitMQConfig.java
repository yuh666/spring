package com.example.spring.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

//@Configuration
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
        factory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.SIMPLE);
        return factory;
    }

    @Bean(name = "topicExchange")
    public TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }


    @Bean
    public Queue queue1() {
        return new Queue("queue1");
    }

    @Bean
    public Queue queue2() {
        return new Queue("queue2");
    }


    @Bean
    public Binding bind1() {
        return BindingBuilder.bind(queue1()).to(exchange()).with("1");
    }

    @Bean
    public Binding bind2() {
        return BindingBuilder.bind(queue2()).to(exchange()).with("2");
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        return new RabbitTemplate(factory);
    }


}
