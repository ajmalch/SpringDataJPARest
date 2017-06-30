package com.example.controller;

import com.example.model.Person;
import com.example.model.SimplePerson;
import com.example.service.PersonService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AjmalCholassery on 4/3/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonRestController.class,secure = false)
public class PersonRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    private Person p1 = new Person( "cliebntIdTest", LocalDate.of(2013, 01, 01),
            10L,"test",LocalDate.of(2020,12,31),
            "Maliha", "Cholasery",LocalDate.of(1985, 01, 24),
            Person.SEX.FEMALE);



    @Test
    public void getPersonTest() throws Exception{
        Mockito.when(personService.getPersonByLastName(Mockito.anyString(),Mockito.eq(Person.class))).thenReturn(p1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/persons/get/Cholasery")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        System.out.println("Result :" + result.getResponse().getContentAsString());
        String expected = "{\"firstname\":\"Maliha\", \"lastname\" : \"Cholasery\"}";
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);

    }


    @Test
    public void searchPersonTest() throws Exception{

        ObjectMapper mapper = new ObjectMapper();
        Person person = new Person();
        person.setFirstname("Maliha");

        List<Person> personList = new ArrayList<>();
        personList.add(p1);

        Mockito.when(personService.searchPersson(Mockito.anyObject())).thenReturn(personList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/persons/search")
                .content(mapper.writeValueAsString(person)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println("Result :" + result.getResponse().getContentAsString());

        TypeReference<List<Person>> personListRef = new TypeReference<List<Person>>() {};
        List<Person> resultList = mapper.readValue(result.getResponse().getContentAsString(),personListRef);

        Assert.assertEquals(resultList.size(), 1 );


        Assert.assertEquals( p1.getLastname(),resultList.get(0).getLastname());

    }

    @Test
    public void updatePersonTest() throws Exception{

        Person p2 = new Person( "cliebntIdTest", LocalDate.of(2013, 01, 01),
                10L,"test",LocalDate.of(2020,12,31),
                "Aqila", "Cholasery",LocalDate.of(1985, 01, 24),
                Person.SEX.FEMALE);
        Mockito.when(personService.updatePerson(Mockito.anyString(),Mockito.anyString())).thenReturn(p2);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/persons/update/cholasery")
                .param("firstname","Aqila")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        System.out.println("Result :" + result.getResponse().getContentAsString());
        String expected = "{\"firstname\":\"Aqila\", \"lastname\" : \"Cholasery\"}";
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);

    }



    @Test
    public void getSimplePersonTest() throws Exception{
        Mockito.when(personService.getPersonByLastName(Mockito.anyString(),Mockito.eq(SimplePerson.class))).thenReturn(new SimplePerson() {
            @Override
            public String getLastname() {
                return "Cholasery";
            }

            @Override
            public String getFirstname() {
                return "Maliha";
            }
        });

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/persons/getsimple/cholasery")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"firstname\":\"Maliha\", \"lastname\" : \"Cholasery\"}";
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);

    }
}
