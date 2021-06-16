package com.intuit.config;

import com.intuit.service.IProductService;
import com.intuit.service.PayrollService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile("payroll")
public class PayrollConfig {

    @Bean
    public IProductService validateService() {
        IProductService validateService = new PayrollService();
        return validateService;
    }
}
