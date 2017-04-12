package com.example.repository;

import com.example.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by AjmalCholassery on 3/25/17.
 */

public interface OrganizationRepository extends JpaRepository<Organization,Long> {
   <T> T findByShortname(String shortname, Class<T> projection);
}
