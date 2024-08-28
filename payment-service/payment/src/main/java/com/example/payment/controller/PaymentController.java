package com.example.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment.model.Payment;
import com.example.payment.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<String> processPayment(@RequestParam Long id, @RequestBody Payment payment) {
        try {
            if (!id.equals(payment.getOrderId())) {
                return new ResponseEntity<>("Order ID mismatch", HttpStatus.BAD_REQUEST);
            }

            paymentService.processPayment(payment);
            return new ResponseEntity<>("Payment processed successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to process payment: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
