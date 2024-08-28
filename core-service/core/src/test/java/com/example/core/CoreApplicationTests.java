package com.example.core;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.core.controller.OrderController;
import com.example.core.model.Order;
import com.example.core.service.OrderService;

@SpringBootTest
public class CoreApplicationTests {

    @Test
    public void testCreateOrder() {
        OrderService mockService = mock(OrderService.class);
        OrderController controller = new OrderController(mockService);

        Order order = new Order(null, 100.0, "test@example.com", "CREATED");
        ResponseEntity<Order> response = controller.createOrder(order);

        assertEquals(200, response.getStatusCodeValue());
    }
}
