package com.example.DS_Assignment2_Monitoring_Microserv.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;
    //    @Value("${rabbitmq.queueJSON.name}")
//    private String jsonQueue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.key.name}")
    private String key;
//    @Value("${rabbitmq.keyJSON.name}")
//    private String keyJSON;

    @Value("${rabbitmq.queue.nameD}")
    private String queue2;
    //    @Value("${rabbitmq.queueJSON.name}")
//    private String jsonQueue;
    @Value("${rabbitmq.exchange.nameD}")
    private String exchange2;
    @Value("${rabbitmq.key.nameD}")
    private String key2;


    //Spring bean for rabbitmq queue
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    //    @Bean
//    public Queue queueJSON(){
//        return new Queue(jsonQueue);
//    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(key);
    }

    @Bean
    public Queue queue2(){
        return new Queue(queue2);
    }

    //    @Bean
//    public Queue queueJSON(){
//        return new Queue(jsonQueue);
//    }
    @Bean
    public TopicExchange exchange2(){
        return new TopicExchange(exchange2);
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(queue2())
                .to(exchange2())
                .with(key2);
    }

    @Bean
    public MessageConverter converter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }



}

