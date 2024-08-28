package com.example.payment.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.payment.exception.InvalidPaymentException;
import com.example.payment.model.Payment;

@Service
public class PaymentService {

    private final RabbitTemplate rabbitTemplate;
    private final RestTemplate restTemplate;

    @Value("${core.service.url}")
    private String coreServiceUrl;

    public PaymentService(RabbitTemplate rabbitTemplate, RestTemplate restTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.restTemplate = restTemplate;
    }

    public void processPayment(Payment payment) {
        validatePayment(payment);

        if (!checkOrderExists(payment.getOrderId())) {
            throw new InvalidPaymentException("Order with ID " + payment.getOrderId() + " does not exist.");
        }


        rabbitTemplate.convertAndSend("payment.exchange", "payment.routing.key", payment);

        System.out.println("Payment processed and sent to queue: " + payment);
    }

    private void validatePayment(Payment payment) {
        if (payment.getCardNumber() == null || !payment.getCardNumber().matches("\\d{16}")) {
            throw new InvalidPaymentException("Invalid card number.");
        }

        if (payment.getOrderId() == null || payment.getOrderId() <= 0) {
            throw new InvalidPaymentException("Invalid order ID.");
        }
    }

    private boolean checkOrderExists(Long orderId) {
        String url = coreServiceUrl + "/orders/" + orderId;

        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            System.err.println("Error checking order existence: " + e.getMessage());
            return false;
        }
    }
}
