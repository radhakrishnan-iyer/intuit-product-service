package com.intuit.exception;

import com.intuit.common.constant.Constants;
import com.intuit.common.model.Response;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {HttpClientErrorException.class, HttpServerErrorException.class})
    protected ResponseEntity<Object> handleConflict(
            Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add(Constants.request_id , MDC.get(Constants.request_id));
        headers.add(Constants.customer_id , MDC.get(Constants.customer_id));

        if(ex instanceof HttpClientErrorException) {
            HttpClientErrorException exception = (HttpClientErrorException) ex;
            Response serviceUnavailibility = new Response();
            serviceUnavailibility.setCorrelationId(MDC.get(Constants.request_id));
            serviceUnavailibility.setMessage(Constants.PROFILE_SERVICE_UNAVAILABLE);
            return ResponseEntity.status(exception.getStatusCode())
                                .headers(headers)
                                .body(serviceUnavailibility);

        }
        if(ex instanceof HttpServerErrorException) {
            HttpServerErrorException exception = (HttpServerErrorException) ex;
            Response serviceUnavailibility = new Response();
            serviceUnavailibility.setCorrelationId(MDC.get(Constants.request_id));
            serviceUnavailibility.setMessage(Constants.PROFILE_SERVICE_UNAVAILABLE);
            return ResponseEntity.status(exception.getStatusCode())
                    .headers(headers)
                    .body(serviceUnavailibility);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .headers(headers)
                            .body("Error while processing the request. Kindly retry");
    }
}
