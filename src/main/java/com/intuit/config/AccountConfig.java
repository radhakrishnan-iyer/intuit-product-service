package com.intuit.config;

import com.intuit.service.AccountsService;
import com.intuit.service.IProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile("accounts")
public class AccountConfig {

    @Bean
    public IProductService validateService() {
        IProductService validateService = new AccountsService();
        return validateService;
    }
}
