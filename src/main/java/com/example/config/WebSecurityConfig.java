package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by AjmalCholassery on 4/19/17.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/","/login**","/webjars/**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
