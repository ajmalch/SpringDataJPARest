package com.example.repository;

import com.example.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AddresssRepository  extends CrudRepository<Address,Long>{

}
