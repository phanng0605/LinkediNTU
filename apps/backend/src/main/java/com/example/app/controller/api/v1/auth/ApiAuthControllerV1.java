package com.example.app.controller.api.v1.auth;

import com.example.app.services.AccountService;
import com.example.app.services.JWTSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class ApiAuthControllerV1 {

    private final AccountService accountService;
    private final JWTSessionService jwtSessionService;

    @Autowired
    public ApiAuthControllerV1(AccountService accountService, JWTSessionService jwtSessionService) {
        this.accountService = accountService;
        this.jwtSessionService = jwtSessionService;
    }

}