package com.travel.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "travel_policy")
public class TravelPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double maxBudget;

    private String allowedClass;

    private boolean active = true;   // ✅ DEFAULT ACTIVE POLICY

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMaxBudget() {
        return maxBudget;
    }

    public void setMaxBudget(Double maxBudget) {
        this.maxBudget = maxBudget;
    }

    public String getAllowedClass() {
        return allowedClass;
    }

    public void setAllowedClass(String allowedClass) {
        this.allowedClass = allowedClass;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
