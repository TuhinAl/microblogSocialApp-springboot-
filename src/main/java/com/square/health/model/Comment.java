package com.square.health.model;

import javafx.geometry.Pos;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/27/21 at 11:38 PM
 **/
@Entity
@Table(name = "comment")
@Data
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 65535, columnDefinition="TEXT")
    private String comment;
    //private Long postId;
    private Long commentBy;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private Date createDate;


    @ManyToOne
    @JoinColumn(name = "post_id",
            referencedColumnName = "id",
            nullable = false)
    private Post post;

}
