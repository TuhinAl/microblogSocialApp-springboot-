package com.square.health.controller;

import com.square.health.dto.CommentDTO;
import com.square.health.service.CommentService;
import com.square.health.util.error_handle.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:25 AM
 **/

@RestController
@RequestMapping("/square-health/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/specific/{postId}")
    Page<CommentDTO> getAllCommentOfSpecificPost(@PathVariable("postId") Long postId,
                                                 @RequestParam int page,
                                                 @RequestParam int size) {
        return commentService.getAllCommentOfSpecificPost(postId, page, size);
    }


    @GetMapping("/specific/blogger/{bloggerId}")
    Page<CommentDTO> getAllCommentOfSpecificBlogger(@PathVariable("bloggerId") Long bloggerId,
                                                    @RequestParam int page,
                                                    @RequestParam int size) {
        return commentService.getAllCommentOfSpecificBlogger(bloggerId, page, size);
    }

    @PutMapping("/update/{commentId}")
    ResponseEntity<ErrorDetails> updateComment(@PathVariable("commentId") Long commentId,
                                               @RequestParam("comment") String com) {
        return commentService.updateComment(commentId, com);
    }

    @DeleteMapping("/delete/{commentId}")
    ResponseEntity<ErrorDetails> deleteComment(@PathVariable("commentId") Long commentId) {
        return commentService.deleteComment(commentId);
    }
}
