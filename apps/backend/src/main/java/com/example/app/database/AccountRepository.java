package com.example.app.database;

import com.example.app.models.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {

    Mono<Account> findByEmail(String email);

    Mono<Account> findByUsername(String username);

    Mono<Account> findByEmailAndPassword(String email, String password);

    Mono<Account> findByUsernameAndPassword(String username, String password);

}
