package com.coloboc.orders.pattern;

import com.coloboc.orders.entity.Order;
import com.coloboc.orders.service.MessageService;
import com.coloboc.orders.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@AllArgsConstructor
public class Outbox {
    @Transactional
    public void insert(Order order) throws SQLException {
        OrderService.insert(order);
        MessageService.insert(order);
    }
}
