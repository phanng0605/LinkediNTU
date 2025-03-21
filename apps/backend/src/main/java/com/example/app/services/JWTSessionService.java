package com.example.app.services;

import com.example.app.exceptions.ModelException;
import com.example.app.exceptions.ServiceException;
import com.example.app.models.Account;
import com.example.app.models.JWTSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface JWTSessionService {

    public Mono<JWTSession> createSession(Account user, boolean rememberMe) throws ServiceException, ModelException;

    public Mono<JWTSession> createSession(Account user, Long expiresAt) throws ServiceException, ModelException;

    public Mono<JWTSession> getSession(String token) throws ServiceException, ModelException;

    public Mono<JWTSession> validateSession(String token) throws ServiceException, ModelException;

    public Flux<JWTSession> getValidSessionByUserId(String userId) throws ServiceException, ModelException;

    public Mono<JWTSession> setActive(String token, Boolean active) throws ServiceException, ModelException;

}
