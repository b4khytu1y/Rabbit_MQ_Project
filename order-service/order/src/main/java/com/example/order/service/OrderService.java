package com.example.order.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.order.model.Order;

@Service
public class OrderService {
    private final RabbitTemplate rabbitTemplate;

    public OrderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // todo
    public void createOrder(Order order) {
        
        // Логика для создания заказа (например, валидация данных)

        // Отправка сообщения в RabbitMQ для дальнейшей обработки
        rabbitTemplate.convertAndSend("order.exchange", "order.routing.key", order);
    }
}
