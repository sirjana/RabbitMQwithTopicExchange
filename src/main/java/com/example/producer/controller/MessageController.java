package com.example.producer.controller;

import com.example.producer.publisher.RabbitMqProducer;
import com.example.producer.Data.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private RabbitMqProducer producer;

    public MessageController(RabbitMqProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/sendMessageToUsa")
    public ResponseEntity<String> sendMessageToUsa(@RequestBody User user) {
        producer.sendMessageToUsa(user);
        return ResponseEntity.ok("This is message from Usa");
    }

    @GetMapping("/sendMessageToEurope")
    public ResponseEntity<String> sendMessageToEurope(@RequestBody User user) {
        producer.sendMessageToEurope(user);
        return ResponseEntity.ok("This is message from Europe");
    }

    @GetMapping("/sendMessageToAsia")
    public ResponseEntity<String> sendMessageToAsia(@RequestBody User user) {
        producer.sendMessageToAsia(user);
        return ResponseEntity.ok("This is message from Asia");
    }

}
