package com.example.app.controller.api.v1;

import com.example.app.exceptions.ModelException;
import com.example.app.exceptions.ServiceException;
import com.example.app.models.JWTSession;
import com.example.app.models.Response;
import com.example.app.controller.api.v1.auth.login.LoginResponseDtoV1;
import com.example.app.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/authenticate")
public class ApiAuthenticateControllerV1 {

    private final AccountService accountService;

    @Autowired
    public ApiAuthenticateControllerV1(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public Mono<Response<LoginResponseDtoV1>> authenticate(ServerWebExchange exchange) {
        JWTSession session = (JWTSession) exchange.getAttributes().get("session");

        if (session == null) {
            return Mono.just(new Response<LoginResponseDtoV1>().setStatus(Response.ResponseStatus.ERROR)
                    .setMessage("Session not found"));
        }

        try {
            return accountService.getAccountById(session.getUserId()).flatMap(user -> {
                LoginResponseDtoV1 loginResponse = new LoginResponseDtoV1();
                loginResponse.setToken(session.getToken());
                loginResponse.setRole(session.getRole());
                loginResponse.setUsername(user.getUsername());
                loginResponse.setEmail(user.getEmail());

                return Mono.just(new Response<LoginResponseDtoV1>().setStatus(Response.ResponseStatus.SUCCESS)
                        .setMessage("User authenticated").setData(loginResponse));

            }).defaultIfEmpty(new Response<LoginResponseDtoV1>().setStatus(Response.ResponseStatus.ERROR)
                    .setMessage("User found empty"));
        } catch (ServiceException | ModelException e) {
            return Mono.just(new Response<LoginResponseDtoV1>().setStatus(Response.ResponseStatus.ERROR)
                    .setMessage("Error finding user"));
        }
    }


}