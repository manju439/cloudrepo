package com.rabbitmq.springbootrabbitmq.service;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class AMQPListener extends MessageListenerAdapter {

    @Autowired
    private MessageListenerAdapter messageListenerAdapter;

    @Autowired
    private SimpleMessageListenerContainer simpleMessageListenerContainer;

    @Value("${amqp.defaultqueue}")
    private String defaultqueue;

    public void receive() {
        Object listener = new Object() {
            public void handleMessage(String foo) {
                System.out.println(foo);
            }
        };
        messageListenerAdapter.setDefaultListenerMethod(listener.toString());
        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);
        simpleMessageListenerContainer.setQueueNames("myQueue");
        simpleMessageListenerContainer.start();
    }
}