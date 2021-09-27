package com.square.health.model;

public enum Role {

    BLOGGER("BLOGGER"),
    ADMIN("ADMIN");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }


}
