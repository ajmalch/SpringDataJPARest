package com.example.service;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Person;
import com.example.model.SimplePerson;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


/**
 * Created by AjmalCholassery on 4/1/17.
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Person getPersson(String firstname){
        return repository.findByFirstname(firstname).orElseThrow(()->new ResourceNotFoundException("Person with first name " +firstname+ " Not Found"));
    }

    public SimplePerson getSimpleperson(String lastname){
        return repository.findByLastname(lastname, SimplePerson.class).orElseThrow(()-> new ResourceNotFoundException("Person Not Found"));
    }

    public Person updatePerson(String firstname, String lastname){
        Person p = repository.findByLastname(lastname,Person.class).orElseThrow(()-> new ResourceNotFoundException("Person Not Found"));
        p.setFirstname(firstname);
        return repository.save(p);
    }

    public Iterable<Person> searchPersson(Person person) {

        Example<Person> example = Example.of(person);
        return repository.findAll(example);
    }
}
