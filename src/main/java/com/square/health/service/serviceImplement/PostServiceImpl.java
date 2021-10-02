package com.square.health.service.serviceImplement;

import com.square.health.dto.PostDTO;
import com.square.health.dto.PostResponseDTO;
import com.square.health.mapper.PostMapper;
import com.square.health.model.Comment;
import com.square.health.model.Post;
import com.square.health.repository.CommentRepository;
import com.square.health.repository.PostRepository;
import com.square.health.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:05 AM
 **/

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostMapper postMapper;

    @Autowired
    CommentRepository commentRepository;

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
     *  (5) Though I know Constructor injection is better than field injection
     *      but i used it to get optimal output without any complexity and short period of time
     *
     */


    @Override
    public Page<PostDTO> getAllActivatePost(int page, int size) {

        /**
         * This API is for Newsfeed
         */
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> post = postRepository.findAllApprovedPost(pageable);

        return post.map(post1 -> postMapper.postResponse(post1));
    }

    @Override
    public Page<PostDTO> getAllDeactivatePost(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        /**
         * This API for admin dashboard (list of post that need to be published by admin)
         */
        Page<Post> post = postRepository.findAllUnPublishedPost(pageable);

        return post.map(post1 -> postMapper.postResponse(post1));
    }

    /**
     *
     * 'PostResponseDTO' object for a Post with all its comments
     *  JSON Structure:
     *  {
     *      post: "String values",
     *
     *      comments:[ {"comment-1"},
     *      {"comment-2"},
     *      {"comment-4"}
     *      .......
     *      {"comment-n"}]
     *  }
     *
     *  I will fetch this api and consume data & put in Thymeleaf fragment.
     *
     */
    @Override
    public PostResponseDTO getPostWithComments(Long postId) {

        PostResponseDTO postResponse = new PostResponseDTO();
        Post post = postRepository.getById(postId);
        List<Comment> comments = commentRepository.findCommentByPostId(postId);
        postResponse.setPost(post);
        postResponse.setComments(comments);

        return postResponse;
    }
}
