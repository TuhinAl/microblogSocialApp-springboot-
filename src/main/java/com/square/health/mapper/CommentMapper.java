package com.square.health.mapper;

import com.square.health.dto.CommentDTO;
import com.square.health.model.Comment;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 11:48 PM
 **/
@Service
public class CommentMapper {


    public Comment saveComment(String comByBlogger, Long bloggerId) {
        Comment comment = new Comment();
        comment.setComments(comByBlogger);
        comment.setCreateDate(new Date());
        comment.setCommentBy(bloggerId);

        return comment;
    }

    public CommentDTO commentResponse(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setComment(comment.getComments());
        return dto;
    }
}
