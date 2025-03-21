package com.example.app.v1;

import com.example.app.IntegrationTest;
import com.example.app.controller.api.v1.auth.login.LoginRequestDtoV1;
import com.example.app.controller.api.v1.auth.login.LoginResponseDtoV1;
import com.example.app.controller.api.v1.auth.register.RegisterRequestDtoV1;
import com.example.app.database.AccountRepository;
import com.example.app.database.JWTSessionRepository;
import com.example.app.v1.helper.AccountTestHelperV1;
import com.example.app.models.JWTSession;
import com.example.app.models.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
public class JWTSessionTestV1 {

    @Autowired
    private JWTSessionRepository jwtSessionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTestHelperV1 accountTest;

    @BeforeEach
    void setup() {
        Mono.zip(
                accountRepository.deleteAll(),
                jwtSessionRepository.deleteAll()
        ).block();
    }

    @Test
    void shouldCheckValidSessionExpiry() {

        RegisterRequestDtoV1 validAccount = new RegisterRequestDtoV1("John", "Doe", "johndoe", "johndoe@gmail.com",
                "password");

        accountTest.register(validAccount);

        Response <LoginResponseDtoV1> loginResponse = accountTest.login(new LoginRequestDtoV1(validAccount.email(), validAccount.password(), false));

        assertNotNull(loginResponse);

        assertEquals(Response.ResponseStatus.SUCCESS, loginResponse.getStatus());

        LoginResponseDtoV1 loginData = loginResponse.getData();

        assertNotNull(loginData);
        String token = loginData.getToken();
        assertNotNull(token);

        JWTSession session = jwtSessionRepository.findByToken(token).block();

        assertNotNull(session);

        assertTrue(session.getExpiresAt() > (session.getCreatedAt()));

    }
}
