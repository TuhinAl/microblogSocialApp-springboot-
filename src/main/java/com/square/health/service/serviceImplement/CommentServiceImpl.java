package com.square.health.service.serviceImplement;

import com.square.health.dto.CommentDTO;
import com.square.health.mapper.CommentMapper;
import com.square.health.model.Comment;
import com.square.health.repository.CommentRepository;
import com.square.health.service.CommentService;
import com.square.health.util.error_handle.BadRequestException;
import com.square.health.util.error_handle.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.square.health.util.StaticData.*;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:05 AM
 **/

@Service
@Transactional
public class CommentServiceImpl implements CommentService {


    @Autowired
    CommentRepository commentRepository;


    @Autowired
    CommentMapper commentMapper;


    /**
     * ============= Special Notes =================
     * <p>
     * (1) I used custom mapper to save users (Admin, Blogger)
     * (2) Used @Transactional annotation for successful transaction,
     * if any transaction (execution) fails, it will rollback to consistent state
     * (3) Threw Exception for every 'Bad' request and if not found
     * any requested resource in database
     * <p>
     * (4) Returned standard Response that is also customised.
     * <p>
     * (5) Though I know Constructor injection is better than field injection
     * but i used it to get optimal output without any complexity and short period of time
     */

    @Override
    public Page<CommentDTO> getAllCommentOfSpecificPost(Long postId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        if (postId == null) {
            throw new BadRequestException(EMPTY_POST_ID);
        }
        /**
         * get all comments of a post with pagination.
         */

        Page<Comment> comment = commentRepository.findCommentByPostId(postId, pageable);

        return comment.map(com -> commentMapper.commentResponse(com));
    }

    @Override
    public Page<CommentDTO> getAllCommentOfSpecificBlogger(Long bloggerId, int page, int size) {

        if (bloggerId == null) {
            throw new BadRequestException(EMPTY_BLOGGER_ID);
        }

        Pageable pageable = PageRequest.of(page, size);
/**
 * get all comments of a blogger with pagination.
 */
        Page<Comment> comment = commentRepository.findCommentByBloggerId(bloggerId, pageable);

        return comment.map(com -> commentMapper.commentResponse(com));
    }

    @Override
    public ResponseEntity<ErrorDetails> updateComment(Long commentId, String com) {

        if (commentId == null) {
            throw new BadRequestException(EMPTY_COMMENT_ID);
        }
        /**
         * update a comment by comment Id
         */
        Comment comment = commentRepository.getById(commentId);
        comment.setComments(com);
        commentRepository.save(comment);


        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                COMMENT_UPDATED, System.currentTimeMillis()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorDetails> deleteComment(Long commentId) {

        if (commentId == null) {
            throw new BadRequestException(EMPTY_COMMENT_ID);
        }
        Comment comment = commentRepository.getById(commentId);
/**
 * delete comment by Id
 */
        commentRepository.delete(comment);

        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                COMMENT_DELETED, System.currentTimeMillis()),
                HttpStatus.OK);
    }

}
