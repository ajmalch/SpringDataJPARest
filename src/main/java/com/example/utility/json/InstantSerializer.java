package com.example.utility.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.Instant;

/**
 * Created by AjmalCholassery on 7/7/17.
 */
public class InstantSerializer extends StdSerializer<Instant> {

    public InstantSerializer(){
        super(Instant.class);
    }

    @Override
    public void serialize(Instant instant
            , JsonGenerator jsonGenerator
            , SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeString(String.valueOf(instant.getEpochSecond()));

    }
}
