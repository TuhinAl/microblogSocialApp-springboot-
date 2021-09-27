package com.square.health.model;

import javax.xml.crypto.Data;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/27/21 at 11:21 PM
 **/

public class Blogger {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Data createDate;
    private Enum role;
    private Long approvedBy;
    private Byte isActive;


}
