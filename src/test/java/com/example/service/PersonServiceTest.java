package com.example.service;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Person;
import com.example.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AjmalCholassery on 7/12/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {

    @MockBean
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    private Person person1, person2;
    private List<Person> personList;

    @Before
    public void setup(){
        person1 = Person.builder()
                .clientId("clientIdTest1")
                .dateOfBirth(LocalDate.of(1985, 01, 24))
                .firstName("Maliha")
                .lastName("Cholassery")
                .effectiveDate(LocalDate.of(2017, 01, 24))
                .sex(Person.SEX.FEMALE)
                .build();

        person2 = Person.builder()
                .clientId("clientIdTest2")
                .dateOfBirth(LocalDate.of(1995, 01, 24))
                .firstName("Aqila")
                .lastName("Cholassery")
                .effectiveDate(LocalDate.of(2017, 05, 24))
                .sex(Person.SEX.FEMALE)
                .build();

        personList = new ArrayList<>(Arrays.asList(person1, person2));

        Mockito.when(personRepository.findByLastName("Cholassery",Person.class)).thenReturn(person1);
        Mockito.when(personRepository.findByLastName("Ajmal", Person.class)).thenReturn(null);

        Mockito.when(personRepository
                .findAll(Mockito.eq(Example
                        .of(Person
                                .builder()
                                .lastName("Cholassery")
                                .firstName("Maliha")
                                .build()))))
                .thenReturn(new ArrayList<>(Arrays.asList(person1)));
        Mockito.when(personRepository
                .findAll(Mockito.eq(Example
                        .of(Person
                                .builder()
                                .lastName("Cholassery")
                                .build()))))
                .thenReturn(personList);

    }


    @Test
    public void getPersonByLastNameShouldReturnPerson(){

        Person person =personService.getPersonByLastName("Cholassery",Person.class);

        Assert.assertEquals(person,person1);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getPersonByLastNameShouldThrowResourceNotFound(){

        Person person =personService.getPersonByLastName("Ajmal",Person.class);

    }

}
