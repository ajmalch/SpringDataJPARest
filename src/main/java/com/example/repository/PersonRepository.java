package com.example.repository;

import com.example.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


/**
 * Created by AjmalCholassery on 3/25/17.
 */
//@RepositoryRestResource(path = "/people",exported = false)
@RepositoryRestResource(path="/people")
public interface PersonRepository extends CrudRepository<Person,Long>, QueryByExampleExecutor<Person> {
//    Person findByLastname(String lastname);


//    Person findByLastname(String lastname);
    <T> Optional<T> findByLastname(String lastname, Class<T> projection);

    Optional<Person> findByFirstname(String firstname);

    <T> T findByClientId(String clientId, Class<T> projection);

}
