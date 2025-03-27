package com.example.app.controller.api.v1.auth.register;

import com.example.app.exceptions.ModelException;
import com.example.app.exceptions.ServiceException;
import com.example.app.models.Response;
import com.example.app.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth/register")
public class ApiAuthRegisterControllerV1 {

    private final AccountService accountService;

    @Autowired
    public ApiAuthRegisterControllerV1(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Mono<Response<RegisterResponseDtoV1>> register(@RequestBody RegisterRequestDtoV1 request) {
        try {
            return accountService.registerAccount(request).map(account -> {
                RegisterResponseDtoV1 registerResponse = new RegisterResponseDtoV1().setUsername(account.getUsername())
                        .setEmail(account.getEmail()).setFirstname(account.getFirstname())
                        .setLastname(account.getLastname());
                Response<RegisterResponseDtoV1> response = new Response<>();
                response.setStatus(Response.ResponseStatus.SUCCESS).setMessage("Registration successful")
                        .setData(registerResponse);

                return response;
            }).defaultIfEmpty(new Response<RegisterResponseDtoV1>(Response.ResponseStatus.ERROR, "Registration failed"))
                    .onErrorResume(e -> Mono.just(new Response<RegisterResponseDtoV1>(Response.ResponseStatus.ERROR,
                            "Registration failed: " + e.getMessage())));
        } catch (ServiceException | ModelException e) {
            return Mono.just(new Response<RegisterResponseDtoV1>(Response.ResponseStatus.ERROR, e.getMessage()));
        }
    }

}
