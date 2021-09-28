package com.square.health.service.serviceImplement;

import com.square.health.dto.CommentDTO;
import com.square.health.mapper.CommentMapper;
import com.square.health.model.Comment;
import com.square.health.repository.CommentRepository;
import com.square.health.service.CommentService;
import com.square.health.util.error_handle.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:05 AM
 **/

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    CommentRepository commentRepository;


    @Autowired
    CommentMapper commentMapper;

    @Override
    public Page<CommentDTO> getAllCommentOfSpecificPost(Long postId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Comment> comment = commentRepository.findCommentByPostId(postId, pageable);

        return comment.map(com -> commentMapper.commentResponse(com));
    }

    @Override
    public Page<CommentDTO> getAllCommentOfSpecificBlogger(Long bloggerId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Comment> comment = commentRepository.findCommentByBloggerId(bloggerId, pageable);

        return comment.map(com -> commentMapper.commentResponse(com));
    }

    @Override
    public ResponseEntity<ErrorDetails> updateComment(Long commentId, String com) {

        Comment comment = commentRepository.getById(commentId);
        comment.setComments(com);
        commentRepository.save(comment);

        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                "Comment updated", System.currentTimeMillis()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorDetails> deleteComment(Long commentId) {
        Comment comment = commentRepository.getById(commentId);

        commentRepository.delete(comment);
        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                "completed deleted", System.currentTimeMillis()),
                HttpStatus.OK);
    }

}
