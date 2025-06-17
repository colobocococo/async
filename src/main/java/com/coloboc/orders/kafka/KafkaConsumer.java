package com.coloboc.orders.kafka;

import com.coloboc.orders.entity.Order;
import com.coloboc.orders.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "second", groupId = "my_consumer")
    public void listen(String message) throws JsonProcessingException, SQLException {
        System.out.println("Received message = " + message);

        Order order = Order.fromJson(message);
        OrderService.update(order);
    }
}
