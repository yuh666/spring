package com.example.spring.config;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareBatchMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//@Configuration
public class RabbitLitsenerConfig {


    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public SimpleMessageListenerContainer listenerContainer1() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.addQueueNames("queue1");
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new ChannelAwareBatchMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                System.out.println("listener1-1:" + message);
                long deliveryTag = message.getMessageProperties().getDeliveryTag();
                channel.basicAck(deliveryTag, true);
            }

            @Override
            public void onMessageBatch(List<Message> messages, Channel channel) {

            }

        });
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer1_1() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.addQueueNames("queue1");
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new ChannelAwareBatchMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                System.out.println("listener1-2:" + message);
                long deliveryTag = message.getMessageProperties().getDeliveryTag();
                channel.basicAck(deliveryTag, true);
            }

            @Override
            public void onMessageBatch(List<Message> messages, Channel channel) {

            }

        });
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer2() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.addQueueNames("queue2");
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new ChannelAwareBatchMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                System.out.println("listener2-1:" + message);
                long deliveryTag = message.getMessageProperties().getDeliveryTag();
                channel.basicAck(deliveryTag, true);
            }

            @Override
            public void onMessageBatch(List<Message> messages, Channel channel) {

            }

        });
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer2_2() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.addQueueNames("queue2");
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new ChannelAwareBatchMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                System.out.println("listener2-2:" + message);
                long deliveryTag = message.getMessageProperties().getDeliveryTag();
                channel.basicAck(deliveryTag, true);
            }

            @Override
            public void onMessageBatch(List<Message> messages, Channel channel) {

            }

        });
        return container;
    }
}
