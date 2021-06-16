package com.intuit.service;

import com.intuit.common.model.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountsServiceTest {
    private AccountsService accountsService;

    @BeforeEach
    public void setUp() {
        accountsService = new AccountsService();
    }

    @Test
    public void testIsValid() {
        Request request = new Request();
        request.setCorrelationId("corrId");
        boolean result = accountsService.isValidRequest(request);
        Assertions.assertTrue(result);
    }
}
