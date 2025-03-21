package com.example.app.controller.api.v1.auth;

import com.example.app.models.Account;

public record AuthResponseDtoV1(String token, Account.Role role, Long createdAt, Long expiresAt) {
}
