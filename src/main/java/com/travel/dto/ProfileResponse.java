package com.travel.dto;

public class ProfileResponse {

    // =========================
    // BASIC USER INFO
    // =========================
    private String name;

    private String email;

    private String role;

    // =========================
    // DEFAULT CONSTRUCTOR
    // =========================
    public ProfileResponse() {
    }

    // =========================
    // GETTERS & SETTERS
    // =========================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
