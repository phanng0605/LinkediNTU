package com.example.app.api;

import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

public class APIService {
    private final String baseUrl;
    private final WebClient webClient;

    // Constructor
    public APIService(String baseUrl) {
        this.baseUrl = baseUrl;
        this.webClient = WebClient.builder().baseUrl(baseUrl)
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // 10 MB
                        .build())
                .build();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public WebClient getWebClient() {
        return webClient;
    }

}