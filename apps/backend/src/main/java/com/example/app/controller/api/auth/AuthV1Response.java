package com.example.app.controller.api.auth;

import com.example.app.models.Account;

public record AuthV1Response(String token, Account.Role role, Long createdAt, Long expiresAt) {
}
