package com.square.health.dto;

import com.square.health.model.Comment;
import com.square.health.model.Post;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 10/1/21 at 9:14 PM
 **/

@Data
@ToString
public class PostResponseDTO {
    private Post post;
    private List<Comment> comments;

}
