package com.example.repository;

import com.example.model.Address;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AddresssRepository  extends CrudRepository<Address,Long>{

    @EntityGraph(attributePaths = {"person"})
    List<Address> findByAddressIdNotNull();

}
