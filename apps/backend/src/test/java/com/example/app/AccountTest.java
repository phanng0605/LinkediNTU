package com.example.app;

import com.example.app.database.AccountRepository;
import com.example.app.services.AccountService;
import com.example.app.models.Account;
import com.example.app.payloads.AccountRequest;
import com.example.app.payloads.LoginRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AccountTest {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    private Account validAccount;

    @BeforeEach
    void setup() {
        accountRepository.deleteAll();
    }

    @Test
    public void shouldCreateAccount() {

    }

    @Test
    public void shouldNotCreateAccount() {
        when(accountService.createAccount(any(AccountRequest.class)))
                .thenReturn(Mono.error(new RuntimeException("Invalid data")));

        webClient.post()
                .uri("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(invalidAccount)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void shouldLogin() {
        when(accountService.login(any(LoginRequest.class)))
                .thenReturn(Mono.just("mocked-jwt-token"));

        webClient.post()
                .uri("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loginRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.token").isEqualTo("mocked-jwt-token");
    }
}