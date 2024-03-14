package com.example.producer.publisher;

import com.example.producer.Data.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key1}")
    private String usaRoutingKey;

    @Value("${rabbitmq.routing.key2}")
    private String europeRoutingKey;

    @Value("${rabbitmq.routing.key3}")
    private String asiaRoutingKey;

    private RabbitTemplate rabbitTemplate;

    public RabbitMqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessageToUsa(User user){
        rabbitTemplate.convertAndSend(exchange,usaRoutingKey,user);
    }
    public void sendMessageToEurope(User user){
        rabbitTemplate.convertAndSend(exchange,europeRoutingKey,user);
    }
    public void sendMessageToAsia(User user){
        rabbitTemplate.convertAndSend(exchange,asiaRoutingKey,user);
    }
}
