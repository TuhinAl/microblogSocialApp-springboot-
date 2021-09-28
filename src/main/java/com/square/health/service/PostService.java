package com.square.health.service;

import com.square.health.dto.PostDTO;
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
public interface PostService {

    Page<PostDTO> getAllActivatePost(int page, int size);

    Page<PostDTO> getAllDeactivatePost(int page, int size);
}
