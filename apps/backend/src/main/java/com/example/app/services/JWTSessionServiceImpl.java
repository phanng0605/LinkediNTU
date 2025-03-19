package com.example.app.services;

import com.example.app.AppEnvironment;
import com.example.app.database.JWTSessionRepository;
import com.example.app.exceptions.ModelException;
import com.example.app.exceptions.ServiceException;
import com.example.app.models.Account;
import com.example.app.models.JWTSession;
import com.example.app.utils.SHA256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
public class JWTSessionServiceImpl implements JWTSessionService {

    private final JWTSessionRepository jwtSessionRepository;

    @Autowired
    public JWTSessionServiceImpl(JWTSessionRepository jwtSessionRepository) {
        this.jwtSessionRepository = jwtSessionRepository;
    }

    public Mono<JWTSession> createSession(Account user, boolean rememberMe) {
        return Mono.fromCallable(() -> SHA256Hash.signWithSecretKey(user.getId() + System.currentTimeMillis(),
                AppEnvironment.SECRET_KEY)).flatMap(token -> {
                    JWTSession newSession = new JWTSession(token, user.getId(), user.getRole(),
                            System.currentTimeMillis(), System.currentTimeMillis() + 1000L * 60 * 60 * 24 * (rememberMe ? 30: 1), true);
                    return jwtSessionRepository.save(newSession);
                });
    }

    public Mono<JWTSession> getSession(String token) throws ServiceException, ModelException {
        return jwtSessionRepository.findByToken(token);
    }

    public Mono<JWTSession> validateSession(String token) throws ServiceException, ModelException {
        return jwtSessionRepository.findByTokenAndExpiresAtGreaterThanAndActive(token, System.currentTimeMillis(), true)
                .switchIfEmpty(Mono.error(new ServiceException("Session not found")));
    }

    public Flux<JWTSession> getValidSessionByUserId(String userId) throws ServiceException, ModelException {
        return jwtSessionRepository.findByUserIdAndExpiresAtGreaterThanAndActive(userId, System.currentTimeMillis(),
                true);
    }

    public Mono<JWTSession> setActive(String token, Boolean active) throws ServiceException, ModelException {
        return jwtSessionRepository.findByToken(token)
                .switchIfEmpty(Mono.error(new ServiceException("Session not found"))).flatMap(session -> {
                    session.setActive(active);
                    return jwtSessionRepository.save(session);
                });
    }

}
