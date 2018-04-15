package com.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AMQPConfig {

    @Value("${spring.rabbitmq.host}")
    private String amqpHostName;

    @Value("${spring.rabbitmq.username}")
    private String amqpUsername;

    @Value("${spring.rabbitmq.password}")
    private String amqpPassword;

    @Value("${amqp.defaultqueue}")
    private String defaultqueue;

    @Value("${amqp.defaultexchange}")
    private String defaultexchange;

    @Value("${amqp.defaultroutingkey}")
    private String defaultroutingkey;

    @Bean
    public CachingConnectionFactory amqpConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(amqpHostName);
        connectionFactory.setUsername(amqpUsername);
        connectionFactory.setPassword(amqpPassword);
        connectionFactory.setVirtualHost(amqpUsername);
        //Recommended settings
        connectionFactory.setRequestedHeartBeat(30);
        connectionFactory.setConnectionTimeout(30000);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory amqpConnectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(amqpConnectionFactory);
        return rabbitTemplate;
    }

    @Bean
    public void amqpDefaultConfig(CachingConnectionFactory amqpConnectionFactory, TopicExchange defaultExchange, Queue defaultQueue) {
        //Set up queue, exchanges and bindings
        RabbitAdmin admin = new RabbitAdmin(amqpConnectionFactory);
        admin.declareQueue(defaultQueue);
        admin.declareExchange(defaultExchange);
        admin.declareBinding(BindingBuilder.bind(defaultQueue).to(defaultExchange).with(defaultroutingkey));
    }

    @Bean
    public TopicExchange defaultExchange() {
        return new TopicExchange(defaultexchange);
    }

    @Bean
    public Queue defaultQueue() {
        return new Queue(defaultqueue);
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(CachingConnectionFactory amqpConnectionFactory) {
        return new SimpleMessageListenerContainer(amqpConnectionFactory);
    }

}