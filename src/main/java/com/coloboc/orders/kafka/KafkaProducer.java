package com.coloboc.orders.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate <String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        if (!message.equals("{}")) kafkaTemplate.send("course", message);
    }
}
