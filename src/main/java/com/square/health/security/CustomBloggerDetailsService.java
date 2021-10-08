/*
package com.square.health.security;

import com.square.health.model.Blogger;
import com.square.health.repository.BloggerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import static com.square.health.util.StaticData.BLOGGER_NOT_EXIST;

*/
/**
 * @author Alauddin Tuhin
 * @project health
 * @created 10/2/21 at 11:43 AM
 **//*

@Service
public class CustomBloggerDetailsService implements UserDetailsService {

    @Autowired
    BloggerRepository bloggerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Blogger blogger = bloggerRepository.findBloggerByEmail(email);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(blogger.getRole().toString()));

        if (blogger.getId() == null) {
            throw new UsernameNotFoundException(BLOGGER_NOT_EXIST);
        }

        return new User(blogger.getEmail(), blogger.getPassword(),authorities);
    }
}
*/
