package com.square.health.service.serviceImplement;

import com.square.health.dto.BloggerDTO;
import com.square.health.dto.PostDTO;
import com.square.health.mapper.BloggerMapper;
import com.square.health.mapper.CommentMapper;
import com.square.health.mapper.PostMapper;
import com.square.health.model.Post;
import com.square.health.repository.BloggerRepository;
import com.square.health.repository.CommentRepository;
import com.square.health.repository.PostRepository;
import com.square.health.service.BloggerService;
import com.square.health.util.error_handle.BadRequestException;
import com.square.health.util.error_handle.ErrorDetails;
import com.square.health.util.error_handle.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:04 AM
 **/

@Service
public class BloggerServiceImpl implements BloggerService {

    @Autowired
    BloggerRepository bloggerRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BloggerMapper bloggerMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    PostMapper postMapper;

    @Override
    public ResponseEntity<ErrorDetails> registerBlogger(BloggerDTO blogger) {

        if (blogger == null) {
            throw new BadRequestException("Empty logger Infos received");
        }

        bloggerRepository.save(bloggerMapper.register(blogger));

        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                "Blogger Registered successfully", System.currentTimeMillis()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorDetails> addCommentToPost(Long bloggerId, String comment) {

        commentRepository.save(commentMapper.saveComment(comment, bloggerId));

        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                "Comment added to the post", System.currentTimeMillis()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorDetails> addPost(PostDTO dto) {

        postRepository.save(postMapper.postSave(dto));

        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                "Posted successful", System.currentTimeMillis()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorDetails> updatePost(Long postId, PostDTO dto) {

        Post post = postRepository.getById(postId);
        if (post == null) {
            throw new ResourceNotFoundException("Post not found");
        }

        post.setPost(dto.getPost());
        postRepository.save(post);

        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                "Post updated successfully", System.currentTimeMillis()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorDetails> deletePost(Long postId) {

        Post post = postRepository.getById(postId);
        if (post == null) {
            throw new ResourceNotFoundException("POst not found");
        }
        postRepository.delete(post);
        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                "Post updated successfully", System.currentTimeMillis()),
                HttpStatus.OK);
    }
}
