package com.example.app.services.implementations;

import com.example.app.controller.api.v1.auth.login.LoginRequestDtoV1;
import com.example.app.controller.api.v1.auth.register.RegisterRequestDtoV1;
import com.example.app.database.AccountRepository;
import com.example.app.exceptions.ModelException;
import com.example.app.exceptions.ServiceException;
import com.example.app.models.Account;
import com.example.app.services.AccountService;
import com.example.app.utils.SHA256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Mono<Account> registerAccount(RegisterRequestDtoV1 account) throws ServiceException, ModelException {
        return accountRepository.findByEmail(account.email())
                .switchIfEmpty(accountRepository.findByUsername(account.username()))
                .flatMap(existing -> Mono.error(new ServiceException("Account with username or email already exists")))
                .then(Mono.defer(() -> {
                    String salt = SHA256Hash.generateSalt();
                    Account newAccount = new Account();
                    newAccount.setFirstname(account.firstname());
                    newAccount.setLastname(account.lastname());
                    newAccount.setRole(Account.Role.USER);
                    newAccount.setUsername(account.username());
                    newAccount.setEmail(account.email());
                    newAccount.setSalt(salt);
                    newAccount.setPassword(SHA256Hash.hash(account.password() + salt));
                    return accountRepository.save(newAccount);
                }));
    }

    public Mono<Account> loginAccount(LoginRequestDtoV1 account) throws ServiceException, ModelException {
        return accountRepository.findByEmail(account.email())
                .switchIfEmpty(Mono.error(new ServiceException("Invalid username or password"))).flatMap(account1 -> {
                    System.out.println(account1.getPassword());
                    if (account1.getPassword().equals(SHA256Hash.hash(account.password() + account1.getSalt()))) {
                        return Mono.just(account1);
                    } else {
                        return Mono.error(new ServiceException("Invalid username or password"));
                    }
                });
    }

    public Mono<Account> getAccountById(String id) throws ServiceException, ModelException {
        return accountRepository.findById(id);
    }

    public Mono<Account> getAccountByUsername(String username) throws ServiceException, ModelException {
        return accountRepository.findByUsername(username);
    }

    public Mono<Account> getAccountByEmail(String email) throws ServiceException, ModelException {
        return accountRepository.findByEmail(email);
    }

    public Mono<Account> updateAccount(Account account) {
        return accountRepository.save(account);
    }
}