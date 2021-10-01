package com.square.health.service.serviceImplement;

import com.square.health.dto.BloggerDTO;
import com.square.health.dto.PostDTO;
import com.square.health.mapper.BloggerMapper;
import com.square.health.mapper.CommentMapper;
import com.square.health.mapper.PostMapper;
import com.square.health.model.Blogger;
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

import static com.square.health.util.StaticData.*;

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

            throw new BadRequestException(EMPTY_REGISTRATION_INFO);
        }

        bloggerRepository.save(bloggerMapper.register(blogger));


        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                BLOGGER_REGISTRATION_SUCCESS_MESSAGE, System.currentTimeMillis()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorDetails> addCommentToPost(Long bloggerId, String comment) {

        Blogger blogger = bloggerRepository.getById(bloggerId);
        if (blogger.getId() == null) {

            throw new ResourceNotFoundException(INVALID_BLOGGER_ID);
        }

        if (blogger.getApproved() == 1 && blogger.getIsActive() == 1) {
            commentRepository.save(commentMapper.saveComment(comment, bloggerId));
        } else {

            throw new BadRequestException(INACTIVE_BLOGGER_API_ACCESS_DENY);
        }



        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                COMMENT_ADDED_TO_POST, System.currentTimeMillis()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorDetails> addPost(PostDTO dto) {

        Blogger blogger = bloggerRepository.getById(dto.getBloggerId());

        if (blogger.getId() == null) {
            throw new ResourceNotFoundException(INVALID_BLOGGER_ID);
        }

        if (blogger.getApproved() == 1 && blogger.getIsActive() == 1) {

            postRepository.save(postMapper.postSave(dto, blogger));
        } else {
            throw new BadRequestException(INACTIVE_BLOGGER_API_ACCESS_DENY);
        }

        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                STATUS_POSTED, System.currentTimeMillis()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorDetails> updatePost(Long postId, PostDTO dto) {

        Blogger blogger = bloggerRepository.getById(dto.getBloggerId());

        Post post = postRepository.getById(postId);

        if (post.getId() == null) {
            throw new ResourceNotFoundException(POST_NOT_EXIST);
        }
        if (blogger.getApproved() == 1 && blogger.getIsActive() == 1) {
            post.setPost(dto.getPost());
            postRepository.save(post);
        }else{
            throw new BadRequestException(INACTIVE_BLOGGER_API_ACCESS_DENY);
        }



        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                POST_UPDATED_MESSAGE, System.currentTimeMillis()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorDetails> deletePost(Long postId) {

        Post post = postRepository.getById(postId);
        if (post.getId() == null) {
            throw new ResourceNotFoundException(POST_NOT_EXIST);
        }
        postRepository.delete(post);

        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                POST_DELETED, System.currentTimeMillis()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorDetails> likePost(Long postId) {
        Post post = postRepository.getById(postId);
        if (post.getId() == null) {
            throw new ResourceNotFoundException(POST_NOT_EXIST);
        }
        Long likes = post.getLikes();
        post.setLikes(likes + 1);
        postRepository.save(post);


        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                POST_LIKED, System.currentTimeMillis()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorDetails> dislikePost(Long postId) {
        Post post = postRepository.getById(postId);
        if (post.getId() == null) {
            throw new ResourceNotFoundException(POST_NOT_EXIST);
        }
        Long dislikes = post.getDislikes();
        post.setDislikes(dislikes + 1);
        postRepository.save(post);


        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                DISLIKED_POST, System.currentTimeMillis()),
                HttpStatus.OK);
    }
}
