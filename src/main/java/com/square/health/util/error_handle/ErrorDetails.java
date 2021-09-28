package com.square.health.util.error_handle;

import lombok.Data;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:09 AM
 **/
@Data
public class ErrorDetails {

    private  int status;
    private  String message;
    private long timestamp;

    public ErrorDetails(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
