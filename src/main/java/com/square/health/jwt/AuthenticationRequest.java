package com.square.health.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 10/2/21 at 10:07 AM
 **/

@Data
@AllArgsConstructor
public class AuthenticationRequest {
    private String username;
    private String password;
}
