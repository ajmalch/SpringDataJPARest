package com.example.service;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Address;
import com.example.model.Person;
import com.example.repository.AddresssRepository;
import com.example.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


/**
 * Created by AjmalCholassery on 4/1/17.
 */
@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private AddresssRepository addressRepository;

    public <T> T getPersonByLastName(String lastname, Class<T> projection){
        Optional<T> person = Optional.ofNullable(repository.findByLastName(lastname, projection));
        return person.orElseThrow(()-> new ResourceNotFoundException("Person Not Found"));
    }


    public Iterable<Person> searchPerson(Person person) {

        Example<Person> example = Example.of(person);
        return repository.findAll(example);
    }

    public Set<Address> getPersonAddressList(String lastName) {

        return repository.findByLastName(lastName,Person.class).getAddresses();
    }

    @CacheEvict(value = "persons", allEntries = true)
    public void clearCache() {
    }
}
