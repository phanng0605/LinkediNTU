package com.example.app.controller.api.v1.auth.login;

public record LoginRequestDtoV1(String email, String password, Boolean rememberMe) {

}