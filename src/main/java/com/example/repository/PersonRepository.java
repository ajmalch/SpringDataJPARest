package com.example.repository;

import com.example.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Created by AjmalCholassery on 3/25/17.
 */
@RepositoryRestResource(path = "/people")
public interface PersonRepository extends CrudRepository<Person,Long> {
//    Person findByLastname(String lastname);


//    Person findByLastname(String lastname);
    <T> T findByLastname(String lastname, Class<T> projection);

}
