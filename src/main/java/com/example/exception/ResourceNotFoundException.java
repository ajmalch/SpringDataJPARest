package com.example.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by AjmalCholassery on 6/3/17.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    Logger logger = LoggerFactory.getLogger("Log");

    public ResourceNotFoundException(String s){
        super(s);
        logger.error(ResourceNotFoundException.class +" thrown");
    }

}
