package com.example.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
    @Bean
    public WebClient WebClient(WebClient.Builder builder) {

        return builder.build();
    }

}
