package com.example.exception.advice;

import com.example.exception.ResourceNotFoundException;
import com.example.exception.model.ResponseMsg;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

/**
 * Created by AjmalCholassery on 6/6/17.
 */
@RestControllerAdvice
public class ExceptionRestControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseMsg handleResourceNotFoundException(ResourceNotFoundException ex){

        return new ResponseMsg(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getClass().toString(),
                ex.getMessage(),
                null
                );
    }

}