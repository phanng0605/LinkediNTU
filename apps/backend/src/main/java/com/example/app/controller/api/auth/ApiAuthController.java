package com.example.app.controller.api.auth;

import com.example.app.controller.RunController;
import com.example.app.exceptions.ModelException;
import com.example.app.exceptions.ServiceException;
import com.example.app.models.Response;
import com.example.app.services.AccountService;
import com.example.app.services.JWTSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/auth")
public class ApiAuthController extends RunController {

    private final AccountService accountService;
    private final JWTSessionService jwtSessionService;

    @Autowired
    public ApiAuthController(AccountService accountService, JWTSessionService jwtSessionService) {
        this.accountService = accountService;
        this.jwtSessionService = jwtSessionService;
    }

    @GetMapping("/check-email")
    public Mono<Response> checkEmail(@RequestParam String email) {
        try {
            return accountService.getAccountByEmail(email)
                    .map(account -> new Response(Response.ResponseStatus.ERROR, "Email already in use"))
                    .defaultIfEmpty(new Response(Response.ResponseStatus.SUCCESS, "Email available"));
        } catch (ServiceException | ModelException e) {
            return Mono.just(new Response(Response.ResponseStatus.ERROR, e.getMessage()));
        }
    }

    @GetMapping("/check-username")
    public Mono<Response> checkUsername(@RequestParam String username) {
        try {
            return accountService.getAccountByUsername(username)
                    .map(account -> new Response(Response.ResponseStatus.ERROR, "Username already in use"))
                    .defaultIfEmpty(new Response(Response.ResponseStatus.SUCCESS, "Username available"));
        } catch (ServiceException | ModelException e) {
            return Mono.just(new Response(Response.ResponseStatus.ERROR, e.getMessage()));
        }
    }
}