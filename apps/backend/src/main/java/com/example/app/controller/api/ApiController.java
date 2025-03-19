package com.example.app.controller.api;

import com.example.app.controller.api.auth.AuthV1Response;
import com.example.app.controller.api.auth.login.LoginV1ResponseDto;
import com.example.app.models.Account;
import com.example.app.models.JWTSession;
import com.example.app.models.Response;
import com.example.app.services.JWTSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final JWTSessionService jwtSessionService;

    @Autowired
    public ApiController(JWTSessionService jwtSessionService) {
        this.jwtSessionService = jwtSessionService;
    }

    @GetMapping
    public Mono<Response<AuthV1Response>> authenticate(ServerWebExchange exchange) {
        JWTSession session = (JWTSession) exchange.getAttributes().get("session");
        System.out.println(session.getToken());
        if (session == null) {
            return Mono.just(new Response<AuthV1Response>().setStatus(Response.ResponseStatus.ERROR)
                    .setMessage("Session not found"));
        }

        return Mono.just(new Response<AuthV1Response>().setStatus(Response.ResponseStatus.SUCCESS)
                .setMessage("Session found").setData(new AuthV1Response(session.getToken(), session.getRole(),
                        session.getCreatedAt(), session.getExpiresAt())));
    }
}
