package com.example.repository;

import com.example.model.Organization;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by AjmalCholassery on 3/25/17.
 */
public interface OrganizationRepository extends CrudRepository<Organization,Long> {
   <T> T findByShortname(String shortname, Class<T> projection);
}
