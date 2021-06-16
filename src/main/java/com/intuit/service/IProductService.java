package com.intuit.service;

import com.intuit.common.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface IProductService {
    Logger logger = LoggerFactory.getLogger(IProductService.class);

    default boolean isValidRequest(Request request) {
        try {
            Thread.sleep(500);
            return true;
        }
        catch (InterruptedException ex) {
            logger.error("Exception while validating request" , ex);
            return false;
        }
    }
}
