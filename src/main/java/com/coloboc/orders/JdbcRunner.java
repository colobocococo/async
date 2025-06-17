package com.coloboc.orders;



import com.coloboc.orders.entity.Order;
import com.coloboc.orders.service.MessageService;
import com.coloboc.orders.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;

import java.sql.SQLException;
import java.util.Properties;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException, JsonProcessingException {
//        String str = MessageService.first();
//        System.out.println(Order.fromJson(str));

//        Order order = new Order(3, 1, 0, "", "new");
//        OrderService.update(order);

//        OrderService.finish();
//        MessageService.finish();

        //OrderService.start();
        //MessageService.start();

//        Order order = new Order(3, 1, 0, "", "new");
//        OrderService.insert(order);

        System.out.println(OrderService.get(2));
    }
}
