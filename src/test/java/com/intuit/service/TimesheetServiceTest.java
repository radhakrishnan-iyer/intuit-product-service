package com.intuit.service;

import com.intuit.common.model.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TimesheetServiceTest {
    private TimesheetService timesheetService;

    @BeforeEach
    public void setUp() {
        timesheetService = new TimesheetService();
    }

    @Test
    public void testIsValid() {
        Request request = new Request();
        request.setCorrelationId("corrId");
        boolean result = timesheetService.isValidRequest(request);
        Assertions.assertTrue(result);
    }
}
