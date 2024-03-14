package com.example.producer.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.queue.name1}")
    private String usaQueue;

    @Value("${rabbitmq.queue.name2}")
    private String europeQueue;

    @Value("${rabbitmq.queue.name3}")
    private String asiaQueue;

    @Value("${rabbitmq.exchange.name}")
    private String weatherExchange;

//    @Value("${rabbitmq.routing.key1}")
//    private String usaRoutingKey;
//
//    @Value("${rabbitmq.routing.key2}")
//    private String europeRoutingKey;
//
//    @Value("${rabbitmq.routing.key3}")
//    private String asiaRoutingKey;
    @Bean
    public Queue usaQueue() {
        return new Queue(usaQueue);
    }
    @Bean
    public Queue europeQueue() {
        return new Queue(europeQueue);
    }
    @Bean
    public Queue asiaQueue() {
        return new Queue(asiaQueue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(weatherExchange);
    }

    @Bean
    public Binding usaBinding() {
        return BindingBuilder
                .bind(usaQueue())
                .to(exchange())
                .with("usa.#");
    }

    @Bean
    public Binding europeBinding() {
        return BindingBuilder
                .bind(europeQueue())
                .to(exchange())
                .with("europe.#");
    }
    @Bean
    public Binding asiaBinding() {
        return BindingBuilder
                .bind(asiaQueue())
                .to(exchange())
                .with("#.rainstorm");
    }
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;


    }
}