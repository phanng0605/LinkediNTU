package com.example.app.services;

import com.example.app.controller.api.v1.auth.login.LoginRequestDtoV1;
import com.example.app.controller.api.v1.auth.register.RegisterRequestDtoV1;
import com.example.app.exceptions.ModelException;
import com.example.app.exceptions.ServiceException;
import com.example.app.models.Account;
import reactor.core.publisher.Mono;

public interface AccountService {

    public Mono<Account> registerAccount(RegisterRequestDtoV1 account) throws ServiceException, ModelException;

    public Mono<Account> loginAccount(LoginRequestDtoV1 account) throws ServiceException, ModelException;

    public Mono<Account> getAccountById(String id) throws ServiceException, ModelException;

    public Mono<Account> getAccountByUsername(String username) throws ServiceException, ModelException;

    public Mono<Account> getAccountByEmail(String email) throws ServiceException, ModelException;

    public Mono<Account> updateAccount(Account account) throws ServiceException, ModelException;
}
