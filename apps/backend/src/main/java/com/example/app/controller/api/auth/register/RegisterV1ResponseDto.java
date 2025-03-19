package com.example.app.controller.api.auth.register;

public class RegisterV1ResponseDto {
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

    public RegisterV1ResponseDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public RegisterV1ResponseDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public RegisterV1ResponseDto setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public RegisterV1ResponseDto setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
}
