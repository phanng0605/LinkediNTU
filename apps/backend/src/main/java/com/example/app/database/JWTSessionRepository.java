package com.example.app.database;

import com.example.app.models.JWTSession;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface JWTSessionRepository extends ReactiveMongoRepository<JWTSession, String> {

    Mono<JWTSession> findByToken(String token);

    Mono<JWTSession> findByTokenAndExpiresAtGreaterThanAndActive(String token, Long expiresAt, Boolean active);

    Mono<JWTSession> findByTokenAndCreatedAtGreaterThanAndExpiresAtGreaterThanAndActive(String token, Long createdAt, Long expiresAt, Boolean active);

    Flux<JWTSession> findByUserId(String userId);

    Flux<JWTSession> findByUserIdAndActive(String userId, Boolean active);

    Flux<JWTSession> findByUserIdAndExpiresAtGreaterThan(String userId, Long expiresAt);

    Flux<JWTSession> findByUserIdAndExpiresAtGreaterThanAndActive(String userId, Long expiresAt, Boolean active);

    Flux<JWTSession> findByUserIdAndCreatedAtGreaterThanAndExpiresAtGreaterThanAndActive(String userId, Long createdAt, Long expiresAt, Boolean active);

}
