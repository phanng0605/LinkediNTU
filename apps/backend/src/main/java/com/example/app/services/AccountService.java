package com.example.app.services;

import com.example.app.controller.api.auth.login.LoginV1RequestDto;
import com.example.app.controller.api.auth.register.RegisterV1RequestDto;
import com.example.app.exceptions.ModelException;
import com.example.app.exceptions.ServiceException;
import com.example.app.models.Account;
import reactor.core.publisher.Mono;

public interface AccountService {

    public Mono<Account> registerAccount(RegisterV1RequestDto account) throws ServiceException, ModelException;

    public Mono<Account> loginAccount(LoginV1RequestDto account) throws ServiceException, ModelException;

    public Mono<Account> getAccountById(String id) throws ServiceException, ModelException;

    public Mono<Account> getAccountByUsername(String username) throws ServiceException, ModelException;

    public Mono<Account> getAccountByEmail(String email) throws ServiceException, ModelException;

}
