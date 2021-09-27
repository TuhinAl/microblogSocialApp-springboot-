package com.square.health.repository;

import com.square.health.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 12:45 AM
 **/
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
