package com.example.exception;

import com.example.utility.json.InstantDeserializer;
import com.example.utility.json.InstantSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

/**
 * Created by AjmalCholassery on 6/6/17.
 */

@Data
@AllArgsConstructor
public class ResponseMsg {

    @JsonSerialize(using = InstantSerializer.class)
    @JsonDeserialize(using = InstantDeserializer.class)
    Instant time;
    int status;
    String error;
    String exception;
    String message;
    String path;
}
