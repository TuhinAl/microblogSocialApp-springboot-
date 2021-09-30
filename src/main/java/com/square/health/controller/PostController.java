package com.square.health.controller;

import com.square.health.dto.PostDTO;
import com.square.health.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:26 AM
 **/

@RestController
@RequestMapping("/api/v1/post")
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
}
