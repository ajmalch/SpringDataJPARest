package com.example.service;

import com.example.model.Person;
import com.example.model.SimplePerson;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AjmalCholassery on 4/1/17.
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Person getPersson(String lastname){
        return repository.findByLastname(lastname, Person.class);
    }

    public SimplePerson getSimpleperson(String lastname){
        return repository.findByLastname(lastname, SimplePerson.class);
    }

    public Person updatePerson(String firstname, String lastname){
        Person p = repository.findByLastname(lastname,Person.class);
        p.setFirstname(firstname);
        return repository.save(p);
    }
}
