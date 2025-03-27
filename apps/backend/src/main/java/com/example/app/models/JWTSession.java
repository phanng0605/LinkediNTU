package com.example.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jwt_sessions")

public class JWTSession {
    @Id // Marks this as the primary key in MongoDB
    private String id; // MongoDB uses String as ID (ObjectId)

    private String token;
    private String userId;
    private Account.Role role;
    private Long createdAt;
    private Long expiresAt;
    private Boolean active;

    public JWTSession(String token, String userId, Account.Role role, Long createdAt, Long expiresAt, Boolean active) {
        this.token = token;
        this.userId = userId;
        this.role = role;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }

    public Account.Role getRole() {
        return role;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRole(Account.Role role) {
        this.role = role;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}