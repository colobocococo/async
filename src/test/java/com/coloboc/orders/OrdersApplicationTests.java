package com.coloboc.orders;

import com.coloboc.orders.entity.Order;
import com.coloboc.orders.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OrdersApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
    void NormalOrder() throws IOException, InterruptedException, SQLException {
		int id = 1001;
		OrderService.insert(new Order(id, 1, 1, "", "new"));

		String status = OrderService.get(id);

		assertEquals("new", status);
    }

	@Test
	void OldOrder() throws SQLException {
		int id = 5;

		assertEquals("finished", OrderService.get(id));
	}
}
