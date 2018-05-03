package com.bilplay.model;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private double budget;
    private boolean isAdmin;
    private int bannedBy;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getBannedBy() {
        return bannedBy;
    }

    public void setBannedBy(int bannedBy) {
        this.bannedBy = bannedBy;
    }
}
