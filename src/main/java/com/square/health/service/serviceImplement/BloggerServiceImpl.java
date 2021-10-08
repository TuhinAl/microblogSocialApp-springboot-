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

import javax.transaction.Transactional;

import static com.square.health.util.StaticData.*;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:04 AM
 **/

@Service
@Transactional
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

    /**
     * ============= Special Notes =================
     *
     * (1) I used custom mapper to save users (Admin, Blogger)
     * (2) Used @Transactional annotation for successful transaction,
     *      if any transaction (execution) fails, it will rollback to consistent state
     * (3) Threw Exception for every 'Bad' request and if not found
     *      any requested resource in database
     *
     *  (4) Returned standard Response that is also customised.
     *
     *   (5) Though I know Constructor injection is better than field injection
     *          but i used it to get optimal output without any complexity and short period of time
     *
     *
     */


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
        /**
         * A Blogger can only comment on other bloggers' post if the
         *  the blogger is Active and Approved (AKA Valid Blogger).
         */
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
        /**
         * A Blogger can only Post a status if  the blogger is Active and Approved (AKA Valid Blogger).
         */
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
        /**
         * A Blogger can only update a status if  the blogger is Active and Approved (AKA Valid Blogger).
         */
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

        /**
         *  a Valid Admin and Blogger can delete the post
         */

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
        System.out.println("=====post======= "+post);
        if (post.getId() == null) {
            throw new ResourceNotFoundException(POST_NOT_EXIST);
        }
        /**
         * a column name 'likes' in post table contains the number of likes for
         * a specific post
         * if a user (Blogger) hit this API, increment the value and update
         */
        Long likes = post.getLikes();
        final long newLike = likes + 1;
        post.setLikes(newLike);
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
        /**
         * a column name 'dislikes' in post table contains the number of dislikes
         * for a specific post.
         * if a user (Blogger) hit this API, increment the value and update
         */
        Long dislikes = post.getDislikes();
        post.setDislikes(dislikes + 1);
        postRepository.save(post);


        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                DISLIKED_POST, System.currentTimeMillis()),
                HttpStatus.OK);
    }
}
