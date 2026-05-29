package com.travel.dto;

public class LoginResponse {

    private String token;
    private String role;
    private String email;
    private String name;

    // =====================
    // DEFAULT CONSTRUCTOR
    // =====================
    public LoginResponse() {
    }

    // =====================
    // PARAMETERIZED CONSTRUCTOR
    // =====================
    public LoginResponse(String token, String role, String email, String name) {
        this.token = token;
        this.role = role;
        this.email = email;
        this.name = name;
    }

    // =====================
    // GETTERS
    // =====================
    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    // =====================
    // SETTERS
    // =====================
    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
