package com.intuit.config;

import com.intuit.service.IProductService;
import com.intuit.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile("payment")
public class PaymentConfig {

    @Bean
    public IProductService validateService() {
        IProductService validateService = new PaymentService();
        return validateService;
    }
}
