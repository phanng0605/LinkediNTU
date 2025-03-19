package com.example.app.controller.api.authenticate;

import com.example.app.exceptions.ModelException;
import com.example.app.exceptions.ServiceException;
import com.example.app.models.Account;
import com.example.app.models.JWTSession;
import com.example.app.models.Response;
import com.example.app.controller.api.auth.login.LoginV1ResponseDto;
import com.example.app.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/authenticate")
public class ApiAuthenticateController {

    private final AccountService accountService;

    @Autowired
    public ApiAuthenticateController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public Mono<Response<LoginV1ResponseDto>> authenticate(ServerWebExchange exchange) {
        JWTSession session = (JWTSession) exchange.getAttributes().get("session");

        if (session == null) {
            return Mono.just(new Response<LoginV1ResponseDto>().setStatus(Response.ResponseStatus.ERROR)
                    .setMessage("Session not found"));
        }

        try {
            return accountService.getAccountById(session.getUserId()).flatMap(user -> {
                LoginV1ResponseDto loginResponse = new LoginV1ResponseDto();
                loginResponse.setToken(session.getToken());
                loginResponse.setRole(session.getRole());
                loginResponse.setUsername(user.getUsername());
                loginResponse.setEmail(user.getEmail());

                return Mono.just(new Response<LoginV1ResponseDto>().setStatus(Response.ResponseStatus.SUCCESS)
                        .setMessage("User authenticated").setData(loginResponse));

            }).defaultIfEmpty(new Response<LoginV1ResponseDto>().setStatus(Response.ResponseStatus.ERROR)
                    .setMessage("User found empty"));
        } catch (ServiceException | ModelException e) {
            return Mono.just(new Response<LoginV1ResponseDto>().setStatus(Response.ResponseStatus.ERROR)
                    .setMessage("Error finding user"));
        }
    }
}