package com.square.health.security;

/*import com.square.health.jwt.jwtFilter.JwtTokenVerifierFilter;
import com.square.health.jwt.jwtFilter.JwtUsernamePasswordAuthenticationFilter;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
/*import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;*/

import static org.springframework.http.HttpMethod.*;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 10/2/21 at 9:37 AM
 **/
/*@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)*/
/*public class MicroblogSecurityConfig extends WebSecurityConfigurerAdapter {*/

   /* @Autowired
    CustomBloggerDetailsService bloggerDetailsService;

    @Autowired
    JwtTokenVerifierFilter tokenVerifierFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(bloggerDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifierFilter(), JwtUsernamePasswordAuthenticationFilter.class)
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

        http.addFilterBefore(tokenVerifierFilter, JwtUsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }*/
/*}*/
