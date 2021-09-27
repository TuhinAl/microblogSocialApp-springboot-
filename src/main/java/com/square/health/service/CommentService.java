package com.square.health.service;

import com.square.health.dto.CommentDTO;
import com.square.health.util.error_handle.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:03 AM
 **/

@Service
public interface CommentService {

    ResponseEntity<CommentDTO> getAllComment(Long commentId);


}
