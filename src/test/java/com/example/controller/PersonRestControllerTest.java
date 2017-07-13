package com.example.controller;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Person;
import com.example.model.SimplePerson;
import com.example.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AjmalCholassery on 4/3/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonRestController.class,secure = false)
public class PersonRestControllerTest {

    private final String basePath = "/persons/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    private Person person1, person2, person3;
    private List<Person> personList, personList2;

    ObjectMapper mapper;

    @Before
    public void setup(){

        person1 = Person.builder()
                    .clientId("clientIdTest1")
                    .birthdt(LocalDate.of(1985, 01, 24))
                    .firstname("Maliha")
                    .lastname("Cholassery")
                    .effectdt(LocalDate.of(2017, 01, 24))
                    .sex(Person.SEX.FEMALE)
                    .build();

        person2 = Person.builder()
                .clientId("clientIdTest2")
                .birthdt(LocalDate.of(1995, 01, 24))
                .firstname("Aqila")
                .lastname("Cholassery")
                .effectdt(LocalDate.of(2017, 05, 24))
                .sex(Person.SEX.FEMALE)
                .build();


         person3 = Person.builder()
                     .clientId("clientIdTest3")
                     .birthdt(LocalDate.of(1995, 01, 24))
                     .firstname("John")
                     .lastname("Doe")
                     .effectdt(LocalDate.of(2017, 01, 01))
                     .sex(Person.SEX.MALE)
                     .build();

        SpelAwareProxyProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();

        personList = new ArrayList<>(Arrays.asList(person1, person2));


        mapper = new ObjectMapper();

        Mockito.when(personService.getPersonByLastName(Mockito.eq("Cholassery"),Mockito.eq(Person.class)))
                .thenReturn(person1);
        Mockito.when(personService.getPersonByLastName(Mockito.eq("Ajmal"),Mockito.eq(Person.class)))
                .thenThrow(new ResourceNotFoundException("Person with lastName Ajmal not found"));
        Mockito.when(personService.getPersonByLastName(Mockito.eq("Cholassery"),Mockito.eq(SimplePerson.class)))
                .thenReturn(projectionFactory.createProjection(SimplePerson.class,person1));
        Mockito.when(personService.getPersonByLastName(Mockito.eq("Ajmal"),Mockito.eq(SimplePerson.class)))
                .thenThrow(new ResourceNotFoundException("Person with lastName Ajmal not found"));
        Mockito.when(personService.searchPersson(Mockito.eq(Person.builder().lastname("Cholassery").build())))
                .thenReturn(personList);
        Mockito.when(personService.searchPersson(Mockito.eq(Person.builder().lastname("Ajmal").build())))
                .thenReturn(new ArrayList<>());
        Mockito.when(personService.updatePerson(Mockito.anyString(), Mockito.eq("Doe")))
                .thenReturn(person3);

    }



    @Test
    public void getPersonShouldReturnPerson() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(basePath+"/get/Cholassery")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientId").value("clientIdTest1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("Maliha"));

    }

    @Test
    public void getPersonShouldReturnPersonNotFound() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(basePath+"get/Ajmal")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Person with lastName Ajmal not found"));

    }

    @Test
    public void getSimplePersonShouldReturnSimplePerson() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(basePath+"getsimple/Cholassery")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientId").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("Maliha"));

    }

    @Test
    public void getSimplePersonShouldReturnPersonNotFound() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(basePath+"getsimple/Ajmal")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Person with lastName Ajmal not found"));

    }

    @Test
    public void searchPersonShouldReturnPersonList() throws Exception{

        Person person = Person.builder().lastname("Cholassery").build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(basePath+"search")
                .content(mapper.writeValueAsString(person)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstname").value("Maliha"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].clientId").value("clientIdTest2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]").doesNotExist());

    }

    @Test
    public void searchPersonShouldReturnEmptyList() throws Exception{

        Person person = Person.builder().lastname("Ajmal").build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(basePath+"search")
                .content(mapper.writeValueAsString(person)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").doesNotExist());

    }

    @Test
    public void searchSimplePersonShouldReturnSimplePersonList() throws Exception{

        Person person = Person.builder().lastname("Cholassery").build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(basePath+"searchsimple")
                .content(mapper.writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstname").value(person1.getFirstname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstname").value(person2.getFirstname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].clientId").doesNotExist());


    }

    @Test
    public void searchSimplePersonShouldReturnEmptyList() throws Exception{

        Person person = Person.builder().lastname("Ajmal").build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(basePath+"searchsimple")
                .content(mapper.writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").doesNotExist());


    }

    @Test
    public void updatePersonTest() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(basePath+"update/Doe")
                .param("firstname","John")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientId").value(person3.getClientId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value(person3.getLastname()));

    }

}
