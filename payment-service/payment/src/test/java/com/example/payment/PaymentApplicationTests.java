package com.example.payment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.payment.controller.PaymentController;
import com.example.payment.model.Payment;
import com.example.payment.service.PaymentService;

@SpringBootTest
public class PaymentApplicationTests {

    @Test
    public void testProcessPayment() {
        PaymentService mockService = mock(PaymentService.class);
        PaymentController controller = new PaymentController(mockService);

        Payment payment = new Payment(1L, "1234567890123456");
        ResponseEntity<String> response = controller.processPayment(1L, payment);

        assertEquals(200, response.getStatusCodeValue());
    }
}
