package com.example.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

/**
 * Created by AjmalCholassery on 6/6/17.
 */

@Data
@AllArgsConstructor
public class ResponseMsg {

    Instant time;
    int status;
    String error;
    String exception;
    String message;
    String path;
}
