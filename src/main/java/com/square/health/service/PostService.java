package com.square.health.service;

import com.square.health.dto.PostDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:03 AM
 **/
@Service
public interface PostService {

    ResponseEntity<PostDTO> getAllPost();

    ResponseEntity<PostDTO> getAllActivePost();
}
