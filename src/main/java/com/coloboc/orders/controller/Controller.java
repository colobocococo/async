package com.coloboc.orders.controller;

import com.coloboc.orders.entity.Order;
import com.coloboc.orders.kafka.KafkaProducer;
import com.coloboc.orders.pattern.Outbox;
import com.coloboc.orders.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/orders")
public class Controller {
    private final KafkaProducer kafkaProducer;
    private final Outbox outbox;

    public Controller(KafkaProducer kafkaProducer, Outbox outbox) {
        this.kafkaProducer = kafkaProducer;
        this.outbox = outbox;
    }

    @PostMapping("/send")
    public String send(@RequestBody String message) {
        kafkaProducer.sendMessage(message);

        return "Success";
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello world";
    }

    @PostMapping("/add")
    public String add(@RequestParam int id, int user_id, int amount) throws SQLException {
        Order order = new Order();
        order.setId(id);
        order.setUser_id(user_id);
        order.setAmount(amount);
        order.setStatus("new");
        outbox.insert(order);

        return "Success";
    }

    @PostMapping("/get")
    public String get(@RequestParam int id) throws SQLException {
        return OrderService.get(id);
    }
}
