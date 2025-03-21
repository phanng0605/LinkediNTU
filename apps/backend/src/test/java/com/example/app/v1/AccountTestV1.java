package com.example.app.v1;

import com.example.app.IntegrationTest;
import com.example.app.controller.api.v1.auth.login.LoginRequestDtoV1;
import com.example.app.controller.api.v1.auth.login.LoginResponseDtoV1;
import com.example.app.controller.api.v1.auth.register.RegisterRequestDtoV1;
import com.example.app.controller.api.v1.auth.register.RegisterResponseDtoV1;
import com.example.app.controller.api.v1.auth.AuthResponseDtoV1;
import com.example.app.database.AccountRepository;
import com.example.app.database.JWTSessionRepository;
import com.example.app.exceptions.ModelException;
import com.example.app.exceptions.ServiceException;
import com.example.app.models.JWTSession;
import com.example.app.services.JWTSessionService;
import com.example.app.v1.helper.AccountTestHelperV1;
import com.example.app.models.Response;
import com.example.app.models.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


@IntegrationTest

public class AccountTestV1 {

    @Autowired
    private AccountTestHelperV1 accountHelper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTSessionRepository jwtSessionRepository;

    @Autowired
    private JWTSessionService jwtSessionService;

    @BeforeEach
    void setup() {
        Mono.zip(
        accountRepository.deleteAll(),
        jwtSessionRepository.deleteAll()
        ).block();
    }

    @Test
    public void shouldCreateAccount() {

        RegisterRequestDtoV1 validAccount = new RegisterRequestDtoV1("John", "Doe", "johndoe", "johndoe@gmail.com",
                "password");

        Response<RegisterResponseDtoV1> responseBody = accountHelper.register(validAccount);

        assertNotNull(responseBody);

        assertEquals(Response.ResponseStatus.SUCCESS, responseBody.getStatus());
        assertEquals("Registration successful", responseBody.getMessage());

        RegisterResponseDtoV1 data = responseBody.getData();

        assertNotNull(data);
        assertEquals("johndoe", data.getUsername());
        assertEquals("John", data.getFirstname());
        assertEquals("Doe", data.getLastname());
        assertEquals("johndoe@gmail.com", data.getEmail());

        Account account = accountRepository.findByUsername("johndoe").block();

        assertNotNull(account);
        assertEquals(validAccount.username(), account.getUsername());
        assertEquals(validAccount.firstname(), account.getFirstname());
        assertEquals(validAccount.lastname(), account.getLastname());
        assertEquals(validAccount.email(), account.getEmail());

    }

    @Test
    public void shouldNotCreateAccountAsAccountExisted() {

        shouldCreateAccount();

        RegisterRequestDtoV1 sameUsernameAccount = new RegisterRequestDtoV1("John", "Doe", "johndoe", "alice@gmail.com", "password");

        Response<RegisterResponseDtoV1> responseBody = accountHelper.register(sameUsernameAccount);

        assertNotNull(responseBody);
        assertEquals(Response.ResponseStatus.ERROR, responseBody.getStatus());

        Account account = accountRepository.findByEmail("alice@gmail.com").block();

        assertNull(account);

        RegisterRequestDtoV1 sameEmailAccount = new RegisterRequestDtoV1("John", "Doe", "alice", "johndoe@gmail.com", "password");

        Response<RegisterResponseDtoV1> responseBody1 = accountHelper.register(sameEmailAccount);

        assertNotNull(responseBody1);
        assertEquals(Response.ResponseStatus.ERROR, responseBody1.getStatus());

        Account account1 = accountRepository.findByUsername("alice").block();

        assertNull(account1);
    }

    @Test
    public void shouldLogin() {
        shouldCreateAccount();

        Account account = accountRepository.findByUsername("johndoe").block();

        assertNotNull(account);

        LoginRequestDtoV1 loginRequest = new LoginRequestDtoV1(account.getEmail(), "password", false);

        Response<LoginResponseDtoV1> responseBody = accountHelper.login(loginRequest);

        assertNotNull(responseBody);
        assertEquals(Response.ResponseStatus.SUCCESS, responseBody.getStatus());
        assertEquals("Login successful", responseBody.getMessage());

        LoginResponseDtoV1 data = responseBody.getData();
        assertNotNull(data);
        assertEquals(account.getUsername(), data.getUsername());
        assertEquals(account.getEmail(), data.getEmail());
        assertNotNull(data.getToken());
    }

    @Test
    public void shouldNotLoginAsAccountDoesNotExist() {
        Account account = accountRepository.findByUsername("johndoe").block();

        assertNull(account);

        LoginRequestDtoV1 loginRequest = new LoginRequestDtoV1("johndoe", "password", false);

        Response<LoginResponseDtoV1> responseBody = accountHelper.login(loginRequest);

        assertEquals(Response.ResponseStatus.ERROR, responseBody.getStatus());
        assertNull(responseBody.getData());
    }

    @Test
    public void shouldNotLoginAsPasswordIsIncorrect() {
        shouldCreateAccount();

        Account account = accountRepository.findByUsername("johndoe").block();

        assertNotNull(account);

        LoginRequestDtoV1 loginRequest = new LoginRequestDtoV1(account.getEmail(), "wrongpassword", false);

        Response<LoginResponseDtoV1> responseBody = accountHelper.login(loginRequest);

        assertEquals(Response.ResponseStatus.ERROR, responseBody.getStatus());
        assertNull(responseBody.getData());
    }

    @Test
    public void shouldAuthenticateUser() {
        shouldCreateAccount();

        Account account = accountRepository.findByUsername("johndoe").block();

        assertNotNull(account);

        LoginRequestDtoV1 loginRequest = new LoginRequestDtoV1(account.getEmail(), "password", false);

        Response<LoginResponseDtoV1> responseBody = accountHelper.login(loginRequest);

        assertEquals(Response.ResponseStatus.SUCCESS, responseBody.getStatus());

        LoginResponseDtoV1 data = responseBody.getData();
        assertNotNull(data);

        String token = data.getToken();

        assertNotNull(token);

        WebTestClient.ResponseSpec loginResponse = accountHelper.authenticate(token);

        WebTestClient.ResponseSpec apiResponse = accountHelper.api(token);

        loginResponse.expectStatus().isOk();
        apiResponse.expectStatus().isOk();

        assertNotNull(loginResponse.expectBody(new ParameterizedTypeReference<Response<LoginResponseDtoV1>>() {
        }).returnResult().getResponseBody());

        assertNotNull(apiResponse.expectBody(new ParameterizedTypeReference<Response<AuthResponseDtoV1>>() {
        }).returnResult().getResponseBody());

    }

    @Test
    public void shouldNotAuthenticateUserAsTokenIsInvalid() {
        shouldCreateAccount();

        Account account = accountRepository.findByUsername("johndoe").block();

        assertNotNull(account);

        LoginRequestDtoV1 loginRequest = new LoginRequestDtoV1(account.getEmail(), "password", false);

        Response<LoginResponseDtoV1> responseBody = accountHelper.login(loginRequest);

        assertEquals(Response.ResponseStatus.SUCCESS, responseBody.getStatus());

        LoginResponseDtoV1 data = responseBody.getData();
        assertNotNull(data);

        String token = data.getToken();

        assertNotNull(token);

        token = token + "invalid";

        WebTestClient.ResponseSpec authResponse = accountHelper.authenticate(token);

        WebTestClient.ResponseSpec apiResponse = accountHelper.api(token);

        authResponse.expectStatus().isUnauthorized();
        apiResponse.expectStatus().isUnauthorized();
    }

    @Test
    public void shouldNotAuthenticateUserAsTokenIsExpired() throws ServiceException, ModelException {

        shouldCreateAccount();

        Account account = accountRepository.findByUsername("johndoe").block();

        assertNotNull(account);

        JWTSession session = jwtSessionService.createSession(account, System.currentTimeMillis() - 1000).block();

        assertNotNull(session);

        WebTestClient.ResponseSpec loginResponse = accountHelper.authenticate(session.getToken());
        WebTestClient.ResponseSpec apiResponse = accountHelper.api(session.getToken());

        loginResponse.expectStatus().isUnauthorized();
        apiResponse.expectStatus().isUnauthorized();

    }

    @Test
    public void shouldLogout() {
        shouldCreateAccount();

        Account account = accountRepository.findByUsername("johndoe").block();

        assertNotNull(account);

        LoginRequestDtoV1 loginRequest = new LoginRequestDtoV1(account.getEmail(), "password", false);

        Response<LoginResponseDtoV1> responseBody = accountHelper.login(loginRequest);

        assertEquals(Response.ResponseStatus.SUCCESS, responseBody.getStatus());

        LoginResponseDtoV1 data = responseBody.getData();
        assertNotNull(data);

        String token = data.getToken();

        assertNotNull(token);

        WebTestClient.ResponseSpec logoutResponse = accountHelper.logout(token);

        logoutResponse.expectStatus().isOk();

        JWTSession session = jwtSessionRepository.findByToken(token).block();

        assertNotNull(session);

        account = accountRepository.findByUsername("johndoe").block();

        assert account != null;
        assertTrue(account.getLastLogout() > session.getCreatedAt());

    }

    @Test
    public void shouldNotLogoutAsTokenInvalid() {
        shouldCreateAccount();

        Account account = accountRepository.findByUsername("johndoe").block();

        assertNotNull(account);

        LoginRequestDtoV1 loginRequest = new LoginRequestDtoV1(account.getEmail(), "password", false);

        Response<LoginResponseDtoV1> responseBody = accountHelper.login(loginRequest);

        assertEquals(Response.ResponseStatus.SUCCESS, responseBody.getStatus());

        LoginResponseDtoV1 data = responseBody.getData();
        assertNotNull(data);

        String token = data.getToken();

        assertNotNull(token);

        token = token + "invalid";

        WebTestClient.ResponseSpec logoutResponse = accountHelper.logout(token);

        logoutResponse.expectStatus().isUnauthorized();
    }

    @Test
    public void shouldNotLogoutAsTokenExpired() throws ServiceException, ModelException {

        shouldCreateAccount();

        Account account = accountRepository.findByUsername("johndoe").block();

        assertNotNull(account);

        JWTSession session = jwtSessionService.createSession(account, System.currentTimeMillis() - 1000).block();

        assertNotNull(session);

        WebTestClient.ResponseSpec logoutResponse = accountHelper.logout(session.getToken());

        logoutResponse.expectStatus().isUnauthorized();

    }

    @Test
    public void shouldNotAuthenticateUserAsUserLogout() throws ServiceException, ModelException {

        shouldCreateAccount();

        Account account = accountRepository.findByUsername("johndoe").block();

        assertNotNull(account);

        ArrayList <String> tokens = new ArrayList <>();

        for (int i = 0; i < 20; ++i){
            // Login 20 times
            LoginRequestDtoV1 loginRequest = new LoginRequestDtoV1(account.getEmail(), "password", false);
            Response<LoginResponseDtoV1> responseBody = accountHelper.login(loginRequest);
            assertEquals(Response.ResponseStatus.SUCCESS, responseBody.getStatus());
            LoginResponseDtoV1 data = responseBody.getData();
            assertNotNull(data);
            String token = data.getToken();
            assertNotNull(token);
            WebTestClient.ResponseSpec loginResponse = accountHelper.authenticate(token);
            loginResponse.expectStatus().isOk();
            WebTestClient.ResponseSpec apiResponse = accountHelper.api(token);
            apiResponse.expectStatus().isOk();

            tokens.add(token);

        }

        // Logout

        // Check  if the user is authenticated
        WebTestClient.ResponseSpec logoutResponse = accountHelper.logout(tokens.get(0));
        logoutResponse.expectStatus().isOk();

        for (int i = 0; i < 20; ++i){
            WebTestClient.ResponseSpec loginResponse = accountHelper.authenticate(tokens.get(i));
            loginResponse.expectStatus().isUnauthorized();
            WebTestClient.ResponseSpec apiResponse = accountHelper.api(tokens.get(i));
            apiResponse.expectStatus().isUnauthorized();
        }

    }
}