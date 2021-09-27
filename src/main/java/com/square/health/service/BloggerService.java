package com.square.health.service;

import com.square.health.dto.AdminDTO;
import com.square.health.dto.BloggerDTO;
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

    ResponseEntity<ErrorDetails> addCommentToPost(Long bloggerId, String commnet);

    ResponseEntity<ErrorDetails> deletePost(Long postId);



}
