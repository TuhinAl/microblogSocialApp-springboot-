package com.square.health.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 12:47 AM
 **/
@Data
@ToString
public class AdminDTO {
    private String name;
    private String email;
    private String password;
}
