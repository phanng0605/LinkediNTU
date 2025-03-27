package com.example.app.controller.api.v1.auth.login;

import com.example.app.models.Account;

public class LoginResponseDtoV1 {
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

    public LoginResponseDtoV1 setToken(String token) {
        this.token = token;
        return this;
    }

    public LoginResponseDtoV1 setRole(Account.Role role) {
        this.role = role;
        return this;
    }

    public LoginResponseDtoV1 setUsername(String username) {
        this.username = username;
        return this;
    }

    public LoginResponseDtoV1 setEmail(String email) {
        this.email = email;
        return this;
    }
}
