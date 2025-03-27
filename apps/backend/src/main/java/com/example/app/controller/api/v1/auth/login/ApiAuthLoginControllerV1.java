package com.example.app.controller.api.v1.auth.login;

import com.example.app.exceptions.ModelException;
import com.example.app.exceptions.ServiceException;
import com.example.app.models.Response;
import com.example.app.services.AccountService;
import com.example.app.services.JWTSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth/login")
public class ApiAuthLoginControllerV1 {

    private final AccountService accountService;
    private final JWTSessionService jwtSessionService;

    @Autowired
    public ApiAuthLoginControllerV1(JWTSessionService jwtSessionService, AccountService accountService) {
        this.jwtSessionService = jwtSessionService;
        this.accountService = accountService;
    }

    @PostMapping
    public Mono<Response<LoginResponseDtoV1>> login(@RequestBody LoginRequestDtoV1 request) {
        try {
            return accountService.loginAccount(request).flatMap(account -> {
                try {
                    System.out.println(account.getEmail());
                    return jwtSessionService.createSession(account, request.rememberMe()).map(session -> {
                        Response<LoginResponseDtoV1> response = new Response<>();
                        LoginResponseDtoV1 loginResponse = new LoginResponseDtoV1();
                        loginResponse.setToken(session.getToken()).setRole(account.getRole())
                                .setUsername(account.getUsername()).setEmail(account.getEmail());
                        response.setStatus(Response.ResponseStatus.SUCCESS).setMessage("Login successful")
                                .setData(loginResponse);
                        return response;
                    });
                } catch (Exception e) {
                    return Mono.just(new Response<LoginResponseDtoV1>(Response.ResponseStatus.ERROR,
                            "Login failed" + e.getMessage()));
                }
            }).defaultIfEmpty(new Response<LoginResponseDtoV1>(Response.ResponseStatus.ERROR, "Login failed"))
                    .onErrorResume(e -> Mono.just(new Response<LoginResponseDtoV1>(Response.ResponseStatus.ERROR,
                            "Login failed: " + e.getMessage())));
        } catch (ServiceException | ModelException e) {
            return Mono.just(new Response<LoginResponseDtoV1>(Response.ResponseStatus.ERROR, e.getMessage()));
        }
    }
}
