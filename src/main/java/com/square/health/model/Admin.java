package com.square.health.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/27/21 at 11:33 PM
 **/
@Entity
@Table(name = "admin")
@Data
@ToString
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private Date createDate;
    private Enum role;


}
