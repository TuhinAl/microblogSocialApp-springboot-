package com.square.health.service;

import com.square.health.dto.CommentDTO;
import com.square.health.util.error_handle.ErrorDetails;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:03 AM
 **/

@Service
public interface CommentService {

    Page<CommentDTO> getAllCommentOfSpecificPost(Long postId, int page, int size);

    Page<CommentDTO> getAllCommentOfSpecificBlogger(Long bloggerId, int page, int size);

    ResponseEntity<ErrorDetails> updateComment(Long commentId, String com);

    ResponseEntity<ErrorDetails> deleteComment(Long commentId);


}
