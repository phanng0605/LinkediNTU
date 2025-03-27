package com.example.app.controller.api.v1.logout;

import com.example.app.controller.api.v1.auth.login.LoginResponseDtoV1;
import com.example.app.exceptions.ModelException;
import com.example.app.exceptions.ServiceException;
import com.example.app.models.JWTSession;
import com.example.app.models.Response;
import com.example.app.services.AccountService;
import com.mongodb.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/logout")
public class ApiLogoutControllerV1 {

    @Autowired
    private final AccountService accountService;

    public ApiLogoutControllerV1(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public Mono<Response<Object>> logout(ServerWebExchange exchange) {

        JWTSession session = (JWTSession) exchange.getAttributes().get("session");

        if (session == null) {
            return Mono.just(new Response<>().setStatus(Response.ResponseStatus.ERROR)
                    .setMessage("Session not found"));
        }

        try {
            return accountService.getAccountById(session.getUserId()).flatMap(user -> {

                user.setLastLogout(System.currentTimeMillis());
                try {
                    return accountService.updateAccount(user).flatMap(updatedUser -> {
                        return Mono.just(new Response<>().setStatus(Response.ResponseStatus.SUCCESS)
                                .setMessage("User logged out"));
                    });
                } catch (ServiceException | ModelException e) {
                    return Mono.just(new Response<>().setStatus(Response.ResponseStatus.ERROR)
                            .setMessage("Error updating user"));
                }

            }).defaultIfEmpty(new Response<>().setStatus(Response.ResponseStatus.ERROR)
                    .setMessage("User found empty"));
        } catch (ServiceException | ModelException e) {
            return Mono.just(new Response<>().setStatus(Response.ResponseStatus.ERROR)
                    .setMessage("Error finding user"));
        }

    }

}
