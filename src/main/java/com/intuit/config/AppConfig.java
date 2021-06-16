package com.intuit.config;

import com.intuit.common.config.SwaggerConfig;
import com.intuit.common.config.WebConfig;
import com.intuit.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import({WebConfig.class,AccountConfig.class, PaymentConfig.class, PayrollConfig.class, TimesheetConfig.class , SwaggerConfig.class})
public class AppConfig {

    @Value("${profile.service.readTimeOut:5000}")
    private int profileServiceReadTimeOut;

    @Value("${profile.service.connectionTimeOut:1000}")
    private int profileServiceConnectionTimeOut;

    @Value("${profile.service.url}")
    private String profileServiceUrl;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(clientHttpRequestFactory());
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(profileServiceReadTimeOut); // setting timeout as read timeout
        factory.setConnectTimeout(profileServiceConnectionTimeOut); // setting timeout as connect timeout
        return factory;
    }

    @Bean
    public ProductService productService() {
        ProductService productService = new ProductService(restTemplate() , profileServiceUrl);
        return productService;
    }
}