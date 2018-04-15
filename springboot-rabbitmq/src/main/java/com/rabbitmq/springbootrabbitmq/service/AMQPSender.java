package com.rabbitmq.springbootrabbitmq.service;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AMQPSender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private TopicExchange defaultExchange;

    @Value("${amqp.defaultroutingkey}")
    private String defaultroutingkey;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        template.convertAndSend(defaultExchange.getName(), defaultroutingkey, "Hello CloudAMQP!");
        System.out.println("Message sent successfully");
    }

}