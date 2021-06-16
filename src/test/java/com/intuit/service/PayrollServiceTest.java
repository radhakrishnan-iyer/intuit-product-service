package com.intuit.service;

import com.intuit.common.model.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PayrollServiceTest {
    private PayrollService payrollService;

    @BeforeEach
    public void setUp() {
        payrollService = new PayrollService();
    }

    @Test
    public void testIsValid() {
        Request request = new Request();
        request.setCorrelationId("corrId");
        boolean result = payrollService.isValidRequest(request);
        Assertions.assertTrue(result);
    }
}
