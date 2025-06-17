package com.coloboc.orders.service;

import com.coloboc.orders.entity.Order;
import com.coloboc.orders.utils.ConnectionManager;
import java.sql.*;

import java.sql.SQLException;

public class MessageService {
    public static void start() throws SQLException {
        var connection = ConnectionManager.open();
        var statement = connection.createStatement();
        String sql = """
                create table messages (
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
                drop table messages;
                """;

        var connection = ConnectionManager.open();
        var statement = connection.prepareStatement(sql);
        statement.execute();
    }

    public static void insert(Order order) throws SQLException {
        String sql = """
                insert into messages (id, user_id, amount, description, status)
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

    public static String first() throws SQLException {
        String sql = """
                SELECT row_to_json(t) AS json_result
                FROM (
                    SELECT *
                    FROM messages
                    LIMIT 1
                ) t
                """;

        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                deleteFirst();
                return resultSet.getString("json_result");
            }
            else {
                return "{}";
            }
        }
    }

    public static void deleteFirst() throws SQLException {
        String sql = """
                delete from messages
                where id =
                (select min(id) from messages);
                """;

        var connection = ConnectionManager.open();
        var statement = connection.prepareStatement(sql);
        statement.execute();
    }
}
