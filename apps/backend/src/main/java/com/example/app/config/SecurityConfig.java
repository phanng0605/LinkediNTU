package com.example.app.config;

import com.example.app.AppEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.session.InMemoryReactiveSessionRegistry;
import org.springframework.security.core.session.ReactiveSessionRegistry;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.session.WebSessionManager;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private AppEnvironment env;

    @Value("${prevent-login:false}")
    private boolean preventLogin;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        DelegatingServerLogoutHandler logoutHandler = new DelegatingServerLogoutHandler(
                new SecurityContextServerLogoutHandler(), new WebSessionServerLogoutHandler());

        http.authorizeExchange(exchanges -> exchanges.anyExchange().permitAll()).httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults()).csrf(ServerHttpSecurity.CsrfSpec::disable)
                .logout(logout -> logout.logoutUrl("/logout").logoutHandler(logoutHandler));
        return http.build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*"); // Use `addAllowedOriginPattern` instead of `addAllowedOrigin`
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter((CorsConfigurationSource) source);
    }

    @Bean
    ReactiveSessionRegistry sessionRegistry() {
        return new InMemoryReactiveSessionRegistry();
    }

    @Bean
    public WebSessionManager webSessionManager() {
        // Emulate SessionCreationPolicy.STATELESS
        return exchange -> Mono.empty();
    }

}