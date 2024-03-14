package com.example.producer.consumer;
import com.example.producer.Data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.name1}"})
    public void UsaConsume(User user)
    {
        logger.info(String.format("Received Usa message -> %s", user.toString()));
    }

    @RabbitListener(queues = {"${rabbitmq.queue.name2}"})
    public void EuropeConsume(User user)
    {
        logger.info(String.format("Received Europe message -> %s", user.toString()));
    }
    @RabbitListener(queues = {"${rabbitmq.queue.name3}"})
    public void AsiaConsume(User user)
    {
        logger.info(String.format("Received Asia message -> %s", user.toString()));
    }


}
