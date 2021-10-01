package com.square.health.mapper;

import com.square.health.dto.PostDTO;
import com.square.health.model.Blogger;
import com.square.health.model.Post;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 10:50 PM
 **/
@Service
public class PostMapper {

   public PostDTO postResponse(Post post) {
        PostDTO dto = new PostDTO();
        dto.setPost(post.getPost());

        return dto;
    }

    public Post postSave(PostDTO dto, Blogger blogger) {
        Post post = new Post();

        post.setPost(dto.getPost());
        post.setBlogger(blogger);
        post.setCreateDate(new Date());
        post.setIsPublished(0);
        return post;
    }

}
