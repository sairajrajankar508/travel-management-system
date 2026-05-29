package com.travel.dto;

import com.travel.entity.User;

public class UserResponse {

    // =========================
    // USER BASIC INFO
    // =========================
    private Long id;

    private String name;

    private String email;

    private String role;

    private String department;

    private boolean active;

    // =========================
    // DEFAULT CONSTRUCTOR
    // =========================
    public UserResponse() {
    }

    // =========================
    // ENTITY → DTO CONSTRUCTOR
    // =========================
    public UserResponse(User user) {

        if (user != null) {

            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();

            this.role = user.getRole() != null
                    ? user.getRole().name()
                    : null;

            this.department = user.getDepartment();
            this.active = user.isActive();
        }
    }

    // =========================
    // GETTERS & SETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
