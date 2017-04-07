package com.example.controller;

import com.example.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by AjmalCholassery on 4/6/17.
 */
@Controller
public class OrganizationMvcController {

    @Autowired
    OrganizationService organizationService;

    @GetMapping(path = "/companies")
    public String organizations(Model model){
        model.addAttribute("companies",organizationService.getAllOrganizations());
        return "companies";
    }

}
