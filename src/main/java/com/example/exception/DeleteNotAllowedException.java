package com.example.exception;


import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

/**
 * Created by AjmalCholassery on 6/3/17.
 */
//@ResponseStatus(METHOD_NOT_ALLOWED)
@Slf4j
public class DeleteNotAllowedException extends  RuntimeException{

    public DeleteNotAllowedException(String s) {
        super(s);
        log.error(DeleteNotAllowedException.class +" thrown and " + METHOD_NOT_ALLOWED +" status is returned",this);

    }
}
