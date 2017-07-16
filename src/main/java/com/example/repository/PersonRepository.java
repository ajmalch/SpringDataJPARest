package com.example.repository;

import com.example.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Created by AjmalCholassery on 3/25/17.
 */
//@RepositoryRestResource(path = "/people",exported = false)
@RepositoryRestResource(path="/people")
public interface PersonRepository extends CrudRepository<Person,Long>, QueryByExampleExecutor<Person> {

  <T> T findByLastName(String lastname, Class<T> projection);

//    Optional<Person> findByLastName(String lastname);

//    List<SimplePerson> findAllProjectedBy(Pageable pageable);
}
