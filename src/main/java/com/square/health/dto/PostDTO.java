package com.square.health.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 12:55 AM
 **/
@Data
@ToString
public class PostDTO {
    private Long bloggerId;
    private String post;
}
