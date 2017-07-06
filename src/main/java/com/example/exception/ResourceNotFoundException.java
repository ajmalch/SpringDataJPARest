package com.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by AjmalCholassery on 6/3/17.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
@Slf4j
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String s){
        super(s);
        log.error(ResourceNotFoundException.class +" thrown");
    }

}
