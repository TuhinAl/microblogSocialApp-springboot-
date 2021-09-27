package com.square.health.repository;

import com.square.health.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 12:44 AM
 **/

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
