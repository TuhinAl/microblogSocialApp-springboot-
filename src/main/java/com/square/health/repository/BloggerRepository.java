package com.square.health.repository;

import com.square.health.model.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 12:43 AM
 **/
@Repository
public interface BloggerRepository extends JpaRepository<Blogger, Long> {

}
