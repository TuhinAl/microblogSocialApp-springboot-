package com.square.health.mapper;

import com.square.health.dto.BloggerDTO;
import com.square.health.model.Blogger;
import org.springframework.stereotype.Service;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 11:38 PM
 **/
@Service
public class BloggerMapper {

    public Blogger register(BloggerDTO dto) {
        Blogger blogger = new Blogger();
        blogger.setFirstName(dto.getFirstName());
        blogger.setLastName(dto.getLastName());
        blogger.setEmail(dto.getEmail());
        blogger.setPassword(dto.getPassword());
        blogger.setIsActive(0);
        blogger.setApproved(0);
        return blogger;
    }
}
