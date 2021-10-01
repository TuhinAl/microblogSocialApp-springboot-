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

import java.util.List;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:05 AM
 **/

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostMapper postMapper;

    @Autowired
    CommentRepository commentRepository;


    @Override
    public Page<PostDTO> getAllActivatePost(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Post> post = postRepository.findAllApprovedPost(pageable);

        return post.map(post1 -> postMapper.postResponse(post1));
    }

    @Override
    public Page<PostDTO> getAllDeactivatePost(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> post = postRepository.findAllUnPublishedPost(pageable);

        return post.map(post1 -> postMapper.postResponse(post1));
    }

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
