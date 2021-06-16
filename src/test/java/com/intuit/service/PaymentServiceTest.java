package com.intuit.service;

import com.intuit.common.model.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentServiceTest {
    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        paymentService = new PaymentService();
    }

    @Test
    public void testIsValid() {
        Request request = new Request();
        request.setCorrelationId("corrId");
        boolean result = paymentService.isValidRequest(request);
        Assertions.assertTrue(result);
    }
}
