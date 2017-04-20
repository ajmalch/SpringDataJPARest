package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by AjmalCholassery on 4/18/17.
 */
@RestController
public class AuthenticationController {

    @GetMapping(path = "/user")
    public Principal getuser(Principal principal){
        return principal;
    }
}
