package com.coloboc.orders.pattern;

import com.coloboc.orders.kafka.KafkaProducer;
import com.coloboc.orders.service.MessageService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class Scheduler {
    private final KafkaProducer kafkaProducer;

    public Scheduler(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Scheduled(fixedRate = 5000)
    public void task() throws SQLException {
        String message = MessageService.first();

        //System.out.println(message);

        kafkaProducer.sendMessage(message);


        //kafkaProducer.sendMessage("success");
    }
}
