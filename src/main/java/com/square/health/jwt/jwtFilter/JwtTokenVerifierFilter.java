/*
package com.square.health.jwt.jwtFilter;

import com.google.common.base.Strings;
import com.square.health.security.CustomBloggerDetailsService;
import com.square.health.util.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.square.health.util.StaticData.*;

*/
/**
 * @author Alauddin Tuhin
 * @project health
 * @created 10/2/21 at 10:51 AM
 **//*

@Component
public class JwtTokenVerifierFilter extends OncePerRequestFilter {

    @Autowired
    CustomBloggerDetailsService bloggerDetailsService;

    */
/**
     *
     * This Filter is create for Every API request, to check the token.
     *  I extend a class 'OncePerRequestFilter' to implement this.
     *
     *  'OncePerRequestFilter' class help me to check every API , and dispatch the API
     *   from servlet if the token is valid and there is no error
     *//*


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        final String authorizationHeader = request.getHeader("Authorization");

        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }


        try {

            */
/**
             * This 'Bearer' is added to test the JWT Token
             *  decode the JWT token into Algorithm, payload and signature
             *  check them every request
             *//*

            String token = authorizationHeader.replace("Bearer ", "");
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(JWT_SIGNATURE.getBytes()))
                    .build().parseClaimsJws(token);


            Claims body = claimsJws.getBody();
            String username = body.getSubject();

            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

            */
/**
             * check authority is granted or not
             *//*

            List<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream().map(
                    m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toList());

            */
/**
             * create authentication by principles and authority
             *//*

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {

            throw new IllegalStateException(INVALID_TOKEN);
        }

        */
/**
         *  chaining the filter
         *//*

        filterChain.doFilter(request, response);
    }
}
*/
