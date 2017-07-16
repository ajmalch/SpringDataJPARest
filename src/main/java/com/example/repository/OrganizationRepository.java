package com.example.repository;

import com.example.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by AjmalCholassery on 3/25/17.
 */

public interface OrganizationRepository extends JpaRepository<Organization,Long> {
   <T> T findByShortName(String shortname, Class<T> projection);

   @RestResource(exported = false)
   @Override
   void delete(Organization organization);
}
