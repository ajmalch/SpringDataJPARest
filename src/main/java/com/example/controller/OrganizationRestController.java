package com.example.controller;

import com.example.model.Organization;
import com.example.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AjmalCholassery on 3/26/17.
 */
@RestController
@RequestMapping(path = "/organizations")
public class OrganizationRestController {

    @Autowired
    OrganizationService organizationService;

    @GetMapping(path = "/get/{shortName}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Organization getOrganization(@PathVariable String shortName){
        return organizationService.findByShortName(shortName);
    }

}
