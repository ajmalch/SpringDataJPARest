package com.example.controller;

import com.example.model.Person;
import com.example.model.SimplePerson;
import com.example.service.PersonService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by AjmalCholassery on 3/25/17.
 */

@Slf4j
@RestController
@RequestMapping(path = "/persons")
public class PersonRestController {


    @Autowired
    private PersonService personService;

    @Autowired
    ProjectionFactory projectionFactory;

    @GetMapping(path = "/get/{lastName}")
    @ApiResponses({
            @ApiResponse(code = 404 , message= "Person Not Found")
    })
    public Person getPerson(@PathVariable String lastName){
        log.info("Invoking PersonRestController.getPerson");
        return personService.getPersonByLastName(lastName,Person.class);
    }

    @GetMapping(path = "/getsimple/{lastName}")
    public SimplePerson getSimplePerson(@PathVariable String lastName){
        log.info("Invoking PersonRestController.getSimplePerson");
        return  personService.getPersonByLastName(lastName, SimplePerson.class);
    }

    @PostMapping(path = "/search")
    public Iterable<Person> searchPerson(@RequestBody Person person){
        log.info("Invoking PersonRestController.searchPerson");
        return  personService.searchPerson(person);
    }

    @PostMapping("/searchsimple")
    public Iterable<SimplePerson> searchSimplePerson(@RequestBody Person person){

        List<Person> personList = (List) personService.searchPerson(person);

        return personList.stream()
                .map((p)->projectionFactory.createProjection(SimplePerson.class,p))
                .collect(Collectors.toList());

    }

}
