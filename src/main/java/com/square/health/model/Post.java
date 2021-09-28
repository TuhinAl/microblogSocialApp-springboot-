package com.square.health.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/27/21 at 11:35 PM
 **/
@Entity
@Table(name = "post")
@Data
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 65535, columnDefinition="TEXT")
    private String post; // need to add constraint Text
    private Long approvedBy;
    private Integer isPublished;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "blogger_id", referencedColumnName = "id", nullable = false)
    private Blogger blogger;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private Collection<Comment> comments;
}
