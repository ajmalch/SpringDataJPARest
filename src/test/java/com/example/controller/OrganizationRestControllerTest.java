package com.example.controller;

import com.example.model.Organization;
import com.example.service.OrganizationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

/**
 * Created by AjmalCholassery on 4/3/17.
 */
@WebMvcTest(value = OrganizationRestController.class,secure = false)
@RunWith(SpringRunner.class)
public class OrganizationRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganizationService organizationService;

    private Organization organization;

    @Before
    public void setup(){
        organization= Organization.builder()
                .clientId("clientIdtest")
                .effectiveDate(LocalDate.of(2016, 04, 01))
                .auditId(10L)
                .searchkey("fis")
                .expiryDate(LocalDate.of(2025,12,31))
                .shortName("FIS")
                .name("FIS Global")
                .build();
        Mockito.when(organizationService.findByShortName(Mockito.eq("FIS")))
                .thenReturn(organization);

    }

    @Test
    public void getOrganizationTest() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/organizations/get/FIS").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientId")
                        .value(organization.getClientId()));

    }

}
