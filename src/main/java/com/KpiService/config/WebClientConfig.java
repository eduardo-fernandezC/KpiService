package com.KpiService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import static org.springframework.web.reactive.function.client.WebClient.builder;

@Configuration
public class WebClientConfig {

    @Bean
    public Builder webClientBuilder() {
        return builder();
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}