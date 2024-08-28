package com.example.payment.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.payment.model.Payment;

@Service
public class PaymentService {
    private final RabbitTemplate rabbitTemplate;

    public PaymentService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void processPayment(Payment payment) {
        // Логика обработки платежа (например, валидация данных)

        // Отправка сообщения в RabbitMQ для дальнейшей обработки
        rabbitTemplate.convertAndSend("payment.exchange", "payment.routing.key", payment);
    }
}
