package com.square.health.service;

import com.square.health.dto.PostDTO;
import com.square.health.dto.PostResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:03 AM
 **/
@Service
public interface PostService {

    Page<PostDTO> getAllActivatePost(int page, int size);

    Page<PostDTO> getAllDeactivatePost(int page, int size);

    PostResponseDTO getPostWithComments(Long postId);

}
