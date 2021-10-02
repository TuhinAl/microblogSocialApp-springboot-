package com.square.health.jwt.jwtFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.square.health.jwt.AuthenticationRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import static com.square.health.util.StaticData.JWT_SIGNATURE;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 10/2/21 at 10:15 AM
 **/

public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    AuthenticationManager authenticationManager;

    public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {

            /**
             * read the data from requestBody (login JSON) to authenticate the  users (Admin & Blogger)
             */
            AuthenticationRequest authenticationRequest = new ObjectMapper().readValue(request.getInputStream(), AuthenticationRequest.class);

            /**
             * Get the Principles and Subject from the Servlet request
             */
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword());

            /**
             * Authenticate the credentials by Spring Security authenticationManager
             */
            Authentication authenticate = authenticationManager.authenticate(authentication);

            /**
             * return authentication
             */
            return authenticate;

        } catch (IOException ioException) {
            throw new RuntimeException();
        }


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        /**
         * if the Request is authenticated then
         * this successfulAuthentication method will execute
         *
         */

        /**
         * Now, generate a JWT token which contains
         *          (1) Header,
         *          (2) Body(payload/claims),
         *          (3) Signature
         *
         *          in my scenario,
         *          (a) Heade --> authResult
         *          (b) Body ---> this is actual payload, or Claims, I put only Authority,
         *               issued time, expire time in JWT body
         *           (c) Signature --> I create a digital signature
         *               using HMAC SHA KEY secret key algorithm wit a secret string
         */

        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusMonths(3)))
                .signWith(Keys.hmacShaKeyFor(JWT_SIGNATURE.getBytes()))
                .compact();

        response.addHeader("Authorization", "Bearer "+token);

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
