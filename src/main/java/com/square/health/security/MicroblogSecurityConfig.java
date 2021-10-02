package com.square.health.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static org.springframework.http.HttpMethod.*;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 10/2/21 at 9:37 AM
 **/

public class MicroblogSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter()
                .addFilterAfter()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers(POST, "/square-health/api/v1/admin").permitAll()
                .antMatchers(PUT, "/square-health/api/v1/admin").hasRole("ADMIN")
                .antMatchers(GET, "/square-health/api/v1/admin").hasRole("ADMIN")
                .antMatchers(DELETE, "/square-health/api/v1/admin").hasRole("ADMIN")
                .antMatchers(POST, "/square-health/api/v1/blogger").permitAll()
                .antMatchers(PUT, "/square-health/api/v1/blogger").hasRole("BLOGGER")
                .antMatchers(GET, "/square-health/api/v1/blogger").hasRole("BLOGGER")
                .antMatchers(DELETE, "/square-health/api/v1/blogger").hasAnyRole("BLOGGER", "ADMIN")
                .anyRequest()
                .authenticated();

        http.addFilterBefore()
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
