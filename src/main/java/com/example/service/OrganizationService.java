package com.example.service;

import com.example.model.Organization;
import com.example.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AjmalCholassery on 4/2/17.
 */
@Service
public class OrganizationService {
    @Autowired
    OrganizationRepository repository;


    public Organization findByShortName(String shortName) {

        return repository.findByShortName(shortName, Organization.class);

    }

    public Iterable<Organization> getAllOrganizations(){
        return  repository.findAll();
    }
}
