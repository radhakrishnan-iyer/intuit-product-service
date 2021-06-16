package com.intuit.service;

import com.intuit.common.model.Request;
import com.intuit.common.model.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ProductServiceTest {

    private ProductService productService;
    private RestTemplate restTemplate;
    private String profileServiceUrl;

    @BeforeEach
    public void setUp() {
        restTemplate = Mockito.mock(RestTemplate.class);
        profileServiceUrl = "http://localhost:8080/";
        productService = new ProductService(restTemplate,profileServiceUrl);
    }

    @Test
    public void testProcess() {
        String endpoint = "endpoint";
        Response response = new Response();
        response.setMessage("RequestAccepted");
        ResponseEntity<Response> responseEntity = ResponseEntity.accepted().body(response);

        Mockito.when(restTemplate.exchange(Mockito.eq(profileServiceUrl+endpoint), Mockito.eq(HttpMethod.POST), Mockito.any(HttpEntity.class), Mockito.eq(Response.class))).thenReturn(responseEntity);

        Request request = new Request();
        request.setCorrelationId("corrId");
        ResponseEntity<Response> actualResponse = productService.process(request , endpoint);
        Assertions.assertEquals(responseEntity , actualResponse);
    }
}
