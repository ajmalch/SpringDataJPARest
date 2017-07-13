package com.example.utility.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;

/**
 * Created by AjmalCholassery on 7/7/17.
 */

public class InstantDeserializer extends StdDeserializer<Instant> {

    public InstantDeserializer(){
        super(Instant.class);
    }

    @Override
    public Instant deserialize(JsonParser jsonParser
            , DeserializationContext deserializationContext) throws IOException {
       return Instant.ofEpochSecond(jsonParser.readValueAs(Long.class));
    }
}
