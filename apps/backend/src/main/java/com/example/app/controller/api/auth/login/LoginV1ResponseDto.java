package com.example.app.controller.api.auth.login;

import com.example.app.models.Account;

public class LoginV1ResponseDto {
    private String token;
    private Account.Role role;
    private String username;
    private String email;

    public String getToken() {
        return token;
    }

    public Account.Role getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public LoginV1ResponseDto setToken(String token) {
        this.token = token;
        return this;
    }

    public LoginV1ResponseDto setRole(Account.Role role) {
        this.role = role;
        return this;
    }

    public LoginV1ResponseDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public LoginV1ResponseDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
