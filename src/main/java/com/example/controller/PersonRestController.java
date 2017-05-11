package com.example.controller;

import com.example.model.Person;
import com.example.model.SimplePerson;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by AjmalCholassery on 3/25/17.
 */
@RestController
@RequestMapping(path = "/persons")
public class PersonRestController {

    @Autowired
    private PersonService personService;

    @GetMapping(path = "/get/{lastname}")
    public Person getPerson(@PathVariable String lastname){
        return personService.getPersson(lastname);
    }

    @GetMapping(path = "/getsimple/{lastname}")
    public SimplePerson getSimplePerson(@PathVariable String lastname){
        return  personService.getSimpleperson(lastname);
    }

    @PostMapping(path = "/search")
    public Person searchPerson(@RequestParam String lastname){
        return  personService.getPersson(lastname);
    }


    @PostMapping(path = "/update/{lastname}")
    public Person updatePerson(@RequestParam String firstname, @PathVariable String lastname){
       return personService.updatePerson(firstname,lastname);
    }

    @PostMapping(path="/webhook")
    public String webhookCallBack(@RequestBody String input){
        return null;
    }
}
