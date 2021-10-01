package com.square.health.repository;

import com.square.health.model.Comment;
import com.square.health.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 12:46 AM
 **/

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT comments FROM comment c WHERE c.post_id = :postId", nativeQuery = true)
    Page<Comment> findCommentByPostId(@Param("postId") Long postId, Pageable page);


    @Query(value = "SELECT comments FROM comment c WHERE c.comment_by = :bloggerId", nativeQuery = true)
    Page<Comment> findCommentByBloggerId(@Param("bloggerId") Long bloggerId, Pageable page);

    @Query(value = "SELECT comments FROM comment c WHERE c.post_id = :postId", nativeQuery = true)
    List<Comment> findCommentByPostId(@Param("postId") Long postId);



}
