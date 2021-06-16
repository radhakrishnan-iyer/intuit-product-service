package com.intuit.service;

import com.intuit.common.constant.Constants;
import com.intuit.common.model.Request;
import com.intuit.common.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class ProductService {

    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final RestTemplate restTemplate;
    private final String profileServiceUrl;

    public ProductService(final RestTemplate restTemplate, final  String profileServiceUrl) {
        this.restTemplate = restTemplate;
        this.profileServiceUrl = profileServiceUrl;
    }

    public ResponseEntity<Response> process(Request request, String endpoint){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add(Constants.request_id, MDC.get(Constants.request_id));
            headers.add(Constants.customer_id, MDC.get(Constants.customer_id));

            HttpEntity<Request> entity = new HttpEntity<>(request, headers);

            return restTemplate.exchange(profileServiceUrl + endpoint, HttpMethod.POST, entity , Response.class);
        }
        catch (Exception ex) {
            logger.error("Exception while invoking profile service {}" , ex);
            throw ex;
        }
    }
}
