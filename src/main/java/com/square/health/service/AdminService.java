package com.square.health.service;

import com.square.health.dto.AdminDTO;
import com.square.health.util.error_handle.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:02 AM
 **/
@Service
public interface AdminService {

    ResponseEntity<ErrorDetails> registerAdmin(AdminDTO admin);

    ResponseEntity<ErrorDetails> approveBlogger(Long bloggerId);

    ResponseEntity<ErrorDetails> deactivateBlogger(Long bloggerId, Boolean status);

    ResponseEntity<ErrorDetails> approveBlogPost(Long postId);

    ResponseEntity<ErrorDetails> deleteBlogPost(Long postId);



}
