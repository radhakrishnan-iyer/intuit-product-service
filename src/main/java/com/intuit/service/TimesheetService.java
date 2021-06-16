package com.intuit.service;

import com.intuit.common.model.Request;

public class TimesheetService implements IProductService {

    @Override
    public boolean isValidRequest(Request request) {
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
