package com.example.spring.config;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource("classpath:mq.properties")
public class RocketMqConfig {


    @Value("${namesrv.addr}")
    private String nameSrvAddr;

    @Bean(name = "simpleProducer")
    public DefaultMQProducer simpleProducer() throws MQClientException {
        DefaultMQProducer producer = new
                DefaultMQProducer("demo_group");
        producer.setNamesrvAddr(nameSrvAddr);
        producer.start();
        return producer;
    }

    @Bean(name = "simpleConsumer")
    public DefaultMQPushConsumer simpleConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
                "demo_group");
        consumer.setNamesrvAddr(nameSrvAddr);
        consumer.subscribe("TopicTest", "*");
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            System.out.printf("%s Receive New Messages: %s %n",
                    Thread.currentThread().getName(), msgs);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
        return consumer;
    }
}
