package com.example.mailing;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.mailing.service.MailingService;

@SpringBootTest
public class MailingApplicationTests {

    @MockBean
    private MailingService mailingService;

    @Test
    public void testSendOrderConfirmationEmail() {
        doNothing().when(mailingService).sendOrderConfirmationEmail("test@example.com", "Order Confirmation", "Your order has been created.");

        mailingService.sendOrderConfirmationEmail("test@example.com", "Order Confirmation", "Your order has been created.");

        verify(mailingService).sendOrderConfirmationEmail("test@example.com", "Order Confirmation", "Your order has been created.");
    }
}
