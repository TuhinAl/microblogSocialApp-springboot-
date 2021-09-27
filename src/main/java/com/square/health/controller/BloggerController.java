package com.square.health.controller;

import com.square.health.service.BloggerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:23 AM
 **/

@RestController
@RequestMapping("/api/v1/blogger")
public class BloggerController {

    private final BloggerService bloggerService;

    public BloggerController(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }

}
