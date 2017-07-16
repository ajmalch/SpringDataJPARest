package com.example.config;

import com.example.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

/**
 * Created by AjmalCholassery on 6/3/17.
 */
@RepositoryEventHandler
@Slf4j
public class PersonEventHandler {

    @HandleBeforeDelete
    public void onBeforeDelete(Person p){
//        throw new DeleteNotAllowedException("Person Delete not Allowed");
        log.info("Person Deleted");
    }
}
