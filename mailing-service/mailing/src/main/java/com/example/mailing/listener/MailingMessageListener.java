package com.example.mailing.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.mailing.service.MailingService;

@Component
public class MailingMessageListener {
    private final MailingService mailingService;

    public MailingMessageListener(MailingService mailingService) {
        this.mailingService = mailingService;
    }

    @RabbitListener(queues = "order.queue")
    public void receiveOrderMessage(String message) {
        System.out.println("Received message: " + message);
        mailingService.sendOrderConfirmationEmail("customer@example.com", "Order Confirmation", "Your order has been created.");
    }

    @RabbitListener(queues = "payment.queue")
    public void receivePaymentMessage(String message) {
        System.out.println("Received message: " + message);
        mailingService.sendOrderConfirmationEmail("customer@example.com", "Payment Confirmation", "Your payment has been received.");
    }
}
