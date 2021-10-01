package com.square.health.controller;

import com.square.health.dto.PostDTO;
import com.square.health.dto.PostResponseDTO;
import com.square.health.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:26 AM
 **/

@RestController
@RequestMapping("/square-health/api/v1/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/activated")
    Page<PostDTO> getAllActivatePost(@RequestParam int page, @RequestParam int size) {
        return postService.getAllActivatePost(page, size);
    }

    @GetMapping("/deactivate")
    Page<PostDTO> getAllDeactivatePost(@RequestParam int page, @RequestParam int size) {
        return postService.getAllActivatePost(page, size);
    }


    @GetMapping("/all/comments/{postId}")
    PostResponseDTO getPostWithComments(@PathVariable("postId") Long postId) {

        return postService.getPostWithComments(postId);
    }

}
