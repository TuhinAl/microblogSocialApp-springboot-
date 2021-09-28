package com.square.health.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/27/21 at 11:21 PM
 **/

@Entity
@Table(name = "blogger")
@Data
@ToString
public class Blogger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private Date createDate;
    private Enum role;
    private Long approvedBy;
    private Integer approved;
    private Integer isActive;

    /**
     * Joining Table: Blogger may have many Posts
     * i set one to many relations
     */

    @OneToMany(mappedBy = "blogger")
    private Collection<Post> posts;

}
