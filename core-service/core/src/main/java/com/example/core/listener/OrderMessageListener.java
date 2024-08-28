package com.example.core.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.core.model.Order;
import com.example.core.service.OrderService;

@Component
public class OrderMessageListener {
    private final OrderService orderService;

    public OrderMessageListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = "order.queue")
    public void receiveOrderMessage(Order order) {
        orderService.createOrder(order);
        System.out.println("Received and created order: " + order);
    }
}
