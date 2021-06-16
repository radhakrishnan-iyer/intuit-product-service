package com.intuit.config;

import com.intuit.service.IProductService;
import com.intuit.service.TimesheetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile("timesheet")
public class TimesheetConfig {

    @Bean
    public IProductService validateService() {
        IProductService validateService = new TimesheetService();
        return validateService;
    }
}
