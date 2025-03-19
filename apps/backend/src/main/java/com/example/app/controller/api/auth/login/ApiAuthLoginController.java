package com.example.app.controller.api.auth.login;

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
@RequestMapping("/api/auth/login")
public class ApiAuthLoginController {

    private final AccountService accountService;
    private final JWTSessionService jwtSessionService;

    @Autowired
    public ApiAuthLoginController(JWTSessionService jwtSessionService, AccountService accountService) {
        this.jwtSessionService = jwtSessionService;
        this.accountService = accountService;
    }

    @PostMapping
    public Mono<Response<LoginV1ResponseDto>> login(@RequestBody LoginV1RequestDto request) {
        try {
            return accountService.loginAccount(request).flatMap(account -> {
                try {
                    System.out.println(account.getEmail());
                    return jwtSessionService.createSession(account, request.rememberMe()).map(session -> {
                        Response<LoginV1ResponseDto> response = new Response<>();
                        LoginV1ResponseDto loginResponse = new LoginV1ResponseDto();
                        response.setStatus(Response.ResponseStatus.SUCCESS);
                        response.setMessage("Login successful");
                        loginResponse.setToken(session.getToken());
                        loginResponse.setRole(account.getRole());
                        loginResponse.setUsername(account.getUsername());
                        loginResponse.setEmail(account.getEmail());
                        response.setData(loginResponse);
                        return response;
                    });
                } catch (Exception e) {
                    return Mono.just(new Response<LoginV1ResponseDto>(Response.ResponseStatus.ERROR,
                            "Login failed" + e.getMessage()));
                }
            }).defaultIfEmpty(new Response<LoginV1ResponseDto>(Response.ResponseStatus.ERROR, "Login failed"))
                    .onErrorResume(e -> Mono.just(new Response<LoginV1ResponseDto>(Response.ResponseStatus.ERROR,
                            "Login failed: " + e.getMessage())));
        } catch (ServiceException | ModelException e) {
            return Mono.just(new Response<LoginV1ResponseDto>(Response.ResponseStatus.ERROR, e.getMessage()));
        }
    }
}
