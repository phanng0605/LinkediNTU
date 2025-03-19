package com.example.app.controller.api.auth.register;

import com.example.app.exceptions.ModelException;
import com.example.app.exceptions.ServiceException;
import com.example.app.models.Response;
import com.example.app.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth/register")
public class ApiAuthRegisterController {

    private final AccountService accountService;

    @Autowired
    public ApiAuthRegisterController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Mono<Response<RegisterV1ResponseDto>> register(@RequestBody RegisterV1RequestDto request) {
        try {
            return accountService.registerAccount(request).map(account -> {
                Response<RegisterV1ResponseDto> response = new Response<>();
                response.setStatus(Response.ResponseStatus.SUCCESS);
                response.setMessage("Registration successful");
                RegisterV1ResponseDto registerResponse = new RegisterV1ResponseDto();
                registerResponse.setUsername(account.getUsername());
                registerResponse.setEmail(account.getEmail());
                registerResponse.setFirstname(account.getFirstname());
                registerResponse.setLastname(account.getLastname());
                response.setData(registerResponse);

                return response;
            }).defaultIfEmpty(new Response<RegisterV1ResponseDto>(Response.ResponseStatus.ERROR, "Registration failed"))
                    .onErrorResume(e -> Mono.just(new Response<RegisterV1ResponseDto>(Response.ResponseStatus.ERROR,
                            "Registration failed: " + e.getMessage())));
        } catch (ServiceException | ModelException e) {
            return Mono.just(new Response<RegisterV1ResponseDto>(Response.ResponseStatus.ERROR, e.getMessage()));
        }
    }

}
