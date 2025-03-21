package com.example.app.controller.api.v1.auth.register;


public class RegisterResponseDtoV1  {
    private String firstname;
    private String lastname;
    private String username;
    private String email;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public RegisterResponseDtoV1 setUsername(String username) {
        this.username = username;
        return this;
    }

    public RegisterResponseDtoV1 setEmail(String email) {
        this.email = email;
        return this;
    }

    public RegisterResponseDtoV1 setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public RegisterResponseDtoV1 setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
}
