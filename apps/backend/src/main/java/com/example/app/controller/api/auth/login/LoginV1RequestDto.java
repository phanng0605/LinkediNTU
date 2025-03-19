package com.example.app.controller.api.auth.login;

public record LoginV1RequestDto(String email, String password, Boolean rememberMe) {

}