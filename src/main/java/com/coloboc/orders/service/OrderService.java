package com.coloboc.orders.service;

import com.coloboc.orders.entity.Order;
import com.coloboc.orders.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderService {
    public static void start() throws SQLException {
        var connection = ConnectionManager.open();
        var statement = connection.createStatement();
        String sql = """
                create table orders (
                    id int primary key,
                    user_id int,
                    amount int,
                    description varchar,
                    status varchar
                )
                """;

        statement.execute(sql);
    }

    public static void finish() throws SQLException {
        String sql = """
                drop table orders;
                """;

        var connection = ConnectionManager.open();
        var statement = connection.prepareStatement(sql);
        statement.execute();
    }

    public static void insert(Order order) throws SQLException {
        String sql = """
                insert into orders(id, user_id, amount, description, status)
                values (?, ?, ?, ?, ?)
                """;

        var connection = ConnectionManager.open();
        var statement = connection.prepareStatement(sql);
        statement.setInt(1, order.getId());
        statement.setInt(2, order.getUser_id());
        statement.setInt(3, order.getAmount());
        statement.setString(4, order.getDescription());
        statement.setString(5, order.getStatus());

        statement.execute();
    }

    public static void update(Order order) throws SQLException {
        System.out.println("updating = " + order);

        String sql = """
                update orders
                set status = ?
                where id = ?
                """;

        var connection = ConnectionManager.open();
        var statement = connection.prepareStatement(sql);

        statement.setString(1, order.getStatus());
        statement.setInt(2, order.getId());
        statement.execute();
    }

    public static String get(int id) throws SQLException {
        String sql = """
                select status
                from orders
                where id = ?
                """;

        var connection = ConnectionManager.open();
        var statement = connection.prepareStatement(sql);

        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (result.next()) return result.getString(1);
        return "failed";
    }
}
