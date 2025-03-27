package com.example.app.v1.helper;
import com.example.app.controller.api.v1.auth.login.LoginRequestDtoV1;
import com.example.app.controller.api.v1.auth.login.LoginResponseDtoV1;
import com.example.app.controller.api.v1.auth.register.RegisterRequestDtoV1;
import com.example.app.controller.api.v1.auth.register.RegisterResponseDtoV1;
import com.example.app.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.WebTestClient;

@Component
public class AccountTestHelperV1 {

    @Autowired
    private WebTestClient webClient;

    public Response<RegisterResponseDtoV1> register(RegisterRequestDtoV1 requestDto){

        return webClient.post().uri("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON).bodyValue(requestDto).exchange().expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<Response<RegisterResponseDtoV1>>() {
                }).returnResult().getResponseBody();
    }

    public Response<LoginResponseDtoV1> login(LoginRequestDtoV1 requestDto){
        return webClient.post().uri("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON).bodyValue(requestDto)
                .exchange().expectStatus().isOk().expectBody(new ParameterizedTypeReference<Response<LoginResponseDtoV1>>() {
                }).returnResult().getResponseBody();
    }

    public WebTestClient.ResponseSpec authenticate(String token) {
        return webClient.get().uri("/api/v1/authenticate")
                .header("Authorization", "Bearer " + token)
                .exchange();
    }

    public WebTestClient.ResponseSpec api(String token) {
        return webClient.get().uri("/api/v1")
                .header("Authorization", "Bearer " + token)
                .exchange();
    }

    public WebTestClient.ResponseSpec logout(String token) {
        return webClient.get().uri("/api/v1/logout")
                .header("Authorization", "Bearer " + token)
                .exchange();
    }
}
