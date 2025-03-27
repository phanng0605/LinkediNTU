package com.example.app.controller.api.v1;

import com.example.app.controller.api.v1.auth.AuthResponseDtoV1;
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
@RequestMapping("/api/v1")
public class ApiControllerV1 {

    private final JWTSessionService jwtSessionService;

    @Autowired
    public ApiControllerV1(JWTSessionService jwtSessionService) {
        this.jwtSessionService = jwtSessionService;
    }

    @GetMapping
    public Mono<Response<AuthResponseDtoV1>> authenticate(ServerWebExchange exchange) {
        JWTSession session = (JWTSession) exchange.getAttributes().get("session");

        if (session == null){
            return Mono.just(new Response<AuthResponseDtoV1>().setStatus(Response.ResponseStatus.ERROR)
                    .setMessage("Session not found"));
        }

        return Mono.just(new Response<AuthResponseDtoV1>().setStatus(Response.ResponseStatus.SUCCESS)
                .setMessage("Session found").setData(new AuthResponseDtoV1(session.getToken(), session.getRole(),
                        session.getCreatedAt(), session.getExpiresAt())));
    }
}
