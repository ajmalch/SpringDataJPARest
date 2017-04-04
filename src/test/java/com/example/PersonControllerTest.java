package com.example;

import com.example.controller.PersonController;
import com.example.model.Person;
import com.example.service.PersonService;
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

/**
 * Created by AjmalCholassery on 4/3/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonController.class,secure = false)
public class PersonControllerTest {
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
        Mockito.when(personService.getPersson(Mockito.anyString())).thenReturn(p1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/persons/get/Cholasery").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println("Result :" + result.getResponse().getContentAsString());
        String exppected = "{\"firstname\":\"Maliha\", \"lastname\" : \"Cholasery\"}";
        JSONAssert.assertEquals(exppected,result.getResponse().getContentAsString(),false);

    }


    @Test
    public void searchPersonTest() throws Exception{
        Mockito.when(personService.getPersson(Mockito.anyString())).thenReturn(p1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/persons/search").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println("Result :" + result.getResponse().getContentAsString());
        String exppected = "{\"firstname\":\"Maliha\", \"lastname\" : \"Cholasery\"}";
        JSONAssert.assertEquals(exppected,result.getResponse().getContentAsString(),false);

    }

}
