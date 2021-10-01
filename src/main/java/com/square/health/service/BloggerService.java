package com.square.health.service;

import com.square.health.dto.AdminDTO;
import com.square.health.dto.BloggerDTO;
import com.square.health.dto.PostDTO;
import com.square.health.util.error_handle.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:02 AM
 **/
@Service
public interface BloggerService {

    ResponseEntity<ErrorDetails> registerBlogger(BloggerDTO blogger);

    ResponseEntity<ErrorDetails> addCommentToPost(Long bloggerId, String comment);

    ResponseEntity<ErrorDetails> addPost(PostDTO dto);

    ResponseEntity<ErrorDetails> updatePost(Long postId, PostDTO dto);

    ResponseEntity<ErrorDetails> deletePost(Long postId);

    ResponseEntity<ErrorDetails> likePost(Long postId);

    ResponseEntity<ErrorDetails> dislikePost(Long postId);




}
