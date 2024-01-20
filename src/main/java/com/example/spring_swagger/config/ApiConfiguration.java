package com.example.spring_swagger.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {
    @Bean
    public GroupedOpenApi personApi() {
        return GroupedOpenApi.builder()
                .group("person")
                .pathsToMatch("/person/**")
                .build();
    }

    @Bean
    public GroupedOpenApi testApi() {
        return GroupedOpenApi.builder()
                .group("test")
                .pathsToMatch("/test/**")
                .build();
    }
}
