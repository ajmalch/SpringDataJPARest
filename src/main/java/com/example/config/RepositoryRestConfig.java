package com.example.config;

import com.example.model.Organization;
import com.example.repository.OrganizationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * Created by AjmalCholassery on 4/1/17.
 */
@Configuration
public class RepositoryRestConfig extends RepositoryRestConfigurerAdapter {


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

        config.setBasePath("/api");

//        config.withEntityLookup().
//                forRepository(PersonRepository.class, Person::getLastname,
//                        (personRepository, s) -> personRepository.findByLastname(s,Person.class));

        config.withEntityLookup().
                forRepository(OrganizationRepository.class, Organization::getShortname,
                        ((organizationRepository, s) -> organizationRepository.findByShortname(s,Organization.class)));
    }

    @Bean
    PersonEventHandler personEventHandler() {
        return new PersonEventHandler();
    }


}
