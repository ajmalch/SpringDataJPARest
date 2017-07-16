package com.example.service;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Person;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Created by AjmalCholassery on 4/1/17.
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public <T> T getPersonByLastName(String lastname, Class<T> projection){
        Optional<T> person = Optional.ofNullable(repository.findByLastName(lastname, projection));
        return person.orElseThrow(()-> new ResourceNotFoundException("Person Not Found"));
    }


    public Iterable<Person> searchPerson(Person person) {

        Example<Person> example = Example.of(person);
        return repository.findAll(example);
    }
}
