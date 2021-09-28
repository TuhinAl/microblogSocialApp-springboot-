package com.square.health.repository;

import com.square.health.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 12:45 AM
 **/
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM post p WHERE p.isPublished = :1 ORDER BY p.create_date DESC", nativeQuery = true)
    Page<Post> findAllApprovedPost(Pageable page);


    @Query(value = "SELECT * FROM post p WHERE p.isPublished = :0 ORDER BY p.create_date DESC", nativeQuery = true)
    Page<Post> findAllUnPublishedPost(Pageable page);
    //Page<Post> findAllApprovedPost(@Param("status") Integer active, Pageable page);

}
