package com.example;

import com.example.controller.OrganizationController;
import com.example.model.Organization;
import com.example.service.OrganizationService;
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
@WebMvcTest(value = OrganizationController.class,secure = false)
@RunWith(SpringRunner.class)
public class OrganizationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganizationService organizationService;



    private Organization o1 = new Organization("cliebntIdtest", LocalDate.of(2016, 04, 01),
            10L,"fis",LocalDate.of(2025,12,31),
            "FIS","FIS Global");

    @Test
    public void getOrganizationTest() throws Exception{
        Mockito.when(organizationService.findByShortname(Mockito.anyString())).thenReturn(o1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/organizations/get/FIS").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{\"clientId\":\"cliebntIdtest\",\"orgname\":\"FIS Global\"}";

        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
    }

}
