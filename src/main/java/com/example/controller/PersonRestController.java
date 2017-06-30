package com.example.controller;

import com.example.model.Person;
import com.example.model.SimplePerson;
import com.example.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by AjmalCholassery on 3/25/17.
 */
@RestController
@RequestMapping(path = "/persons")
public class PersonRestController {

    Logger logger = LoggerFactory.getLogger("Log");

    @Autowired
    private PersonService personService;

    @GetMapping(path = "/get/{lastname}")
    public Person getPerson(@PathVariable String lastname){
        logger.info("Invoking PersonRestController.getPerson");
        return personService.getPersonByLastName(lastname,Person.class);
    }

    @GetMapping(path = "/getsimple/{lastname}")
    public SimplePerson getSimplePerson(@PathVariable String lastname){
        logger.info("Invoking PersonRestController.getSimplePerson");
        return  personService.getPersonByLastName(lastname, SimplePerson.class);
    }

    @PostMapping(path = "/search")
    public Iterable<Person> searchPerson(@RequestBody Person person){
        logger.info("Invoking PersonRestController.searchPerson");
        return  personService.searchPersson(person);
    }


    @PostMapping(path = "/update/{lastname}")
    public Person updatePerson(@RequestParam String firstname, @PathVariable String lastname){
        logger.info("Invoking PersonRestController.updatePerson");
        return personService.updatePerson(firstname,lastname);
    }
}
