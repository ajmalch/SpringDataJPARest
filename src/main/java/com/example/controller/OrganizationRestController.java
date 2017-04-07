package com.example.controller;

import com.example.model.Organization;
import com.example.service.OrganizationService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AjmalCholassery on 3/26/17.
 */
@Data
@RestController
@RequestMapping(path = "/organizations")
public class OrganizationRestController {

    @Autowired
    OrganizationService organizationService;

    @GetMapping(path = "/get/{shortname}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Organization getOrganization(@PathVariable String shortname){
        return organizationService.findByShortname(shortname);
    }

}
