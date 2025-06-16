package com.example.provaeliel.models.enums;

public enum RoleUser {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    RoleUser(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
