package com.example.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

/**
 * Created by AjmalCholassery on 6/3/17.
 */
@ResponseStatus(METHOD_NOT_ALLOWED)
public class DeleteNotAllowedException extends  RuntimeException{

    Logger logger = LoggerFactory.getLogger("Log");
    public DeleteNotAllowedException(String s) {
        super(s);
        logger.error(DeleteNotAllowedException.class +" thrown and " + METHOD_NOT_ALLOWED +" status is returned",this);

    }
}
