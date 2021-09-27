package com.square.health.model;

import java.util.Date;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/27/21 at 11:35 PM
 **/

public class Post {

    private Long id;
    private String post; // need to add constraint Text
    private Long approvedBy;
    private Byte isPublished;
    private Date createDate;
}
