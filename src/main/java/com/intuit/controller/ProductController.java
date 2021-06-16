package com.intuit.controller;

import com.intuit.common.constant.Constants;
import com.intuit.common.model.Request;
import com.intuit.common.model.Response;
import com.intuit.common.model.profile.Profile;
import com.intuit.service.IProductService;
import com.intuit.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.intuit.common.constant.Constants.*;

@RestController
@RequestMapping(value = "/product" , produces = "application/json", consumes = "application/json")
public class ProductController {

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private IProductService validateService;

    @RequestMapping(value = "/profile/create" , method = RequestMethod.POST)
    public ResponseEntity<Response> createProfile(@RequestBody Request request) {
        logger.info("Request Id : {}" , MDC.get(Constants.request_id) );
        productService.process(request , "create");
        Response response = new Response();
        response.setCorrelationId(MDC.get(Constants.request_id));
        response.setMessage(SUCCESS_MESSAGE);
        return ResponseEntity.accepted().body(response);
    }

    @RequestMapping(value = "/profile/validate" , method = RequestMethod.POST)
    public ResponseEntity<Response> validate(@RequestBody Request request) {
        logger.info("Validation request : {} ; requestId : {}" , request ,MDC.get(Constants.request_id) );
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set(Constants.request_id , MDC.get(request_id));
        httpHeaders.set(Constants.customer_id, MDC.get(customer_id));

        if(validateService.isValidRequest(request)) {
            Response response = new Response();
            response.setCorrelationId(MDC.get(Constants.request_id));
            response.setMessage("Valid");
            return ResponseEntity.status(HttpStatus.OK)
                    .headers(httpHeaders)
                    .body(response);
        } else {
            Response response = new Response();
            response.setCorrelationId(MDC.get(Constants.request_id));
            response.setMessage("Invalid");
            return ResponseEntity.status(HttpStatus.OK)
                    .headers(httpHeaders)
                    .body(response);
        }
    }

    @RequestMapping(value = "/profile/update" , method = RequestMethod.POST)
    public ResponseEntity<Response> update(@RequestBody Request request) {
        logger.info("Request Id : {}" , MDC.get(Constants.request_id) );
        productService.process(request , "update");
        Response response = new Response();
        response.setCorrelationId(MDC.get(Constants.request_id));
        response.setMessage(SUCCESS_MESSAGE);
        return ResponseEntity.accepted().body(response);
    }

    @RequestMapping(value = "/transaction/get" , method = RequestMethod.POST)
    public ResponseEntity<Response> getTransactionStatus(@RequestBody Request request) {
        logger.info("Request Id : {} to get status of transaction {}" , MDC.get(Constants.request_id) , request.getCorrelationId());
        ResponseEntity response = productService.process(request , "getTransactionStatus");
        return response;
    }

    @RequestMapping(value = "/profile/get/{profileId}" , method = RequestMethod.GET)
    public ResponseEntity<Response> getProfile(@PathVariable String profileId) {
        logger.info("Request Id : {} to get details of profileId {}" , MDC.get(Constants.request_id) , profileId);
        Profile profile = new Profile();
        profile.setProfileId(profileId);
        Request request = new Request();
        request.setProfile(profile);
        ResponseEntity response = productService.process(request , "getProfile");
        return response;
    }
}
