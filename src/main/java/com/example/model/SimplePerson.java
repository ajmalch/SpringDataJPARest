package com.example.model;

import org.springframework.data.rest.core.config.Projection;

/**
 * Created by AjmalCholassery on 3/29/17.
 */

@Projection(name="simplePerson", types = Person.class)
public interface SimplePerson {

    String getLastname();
    String getFirstname();
}
