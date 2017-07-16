package com.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by AjmalCholassery on 3/29/17.
 */

@Projection(name="simplePerson", types = Person.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface SimplePerson {

    String getLastName();
    String getFirstName();
}
