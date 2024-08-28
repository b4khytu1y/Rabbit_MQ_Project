package com.example.order;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.order.controller.OrderController;
import com.example.order.model.Order;
import com.example.order.service.OrderService;

@SpringBootTest
public class OrderApplicationTests {

    @Test
    public void testCreateOrder() {
        OrderService mockService = mock(OrderService.class);
        OrderController controller = new OrderController(mockService);

        Order order = new Order(1L, 100.0, "test@example.com");
        ResponseEntity<String> response = controller.createOrder(order);

        assertEquals(201, response.getStatusCodeValue());
    }
}
