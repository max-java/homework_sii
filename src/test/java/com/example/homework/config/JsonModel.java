package com.example.homework.config;

import com.example.homework.bean.TransactionEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class JsonModel {
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return RepositoryRestConfigurer.withConfig(config -> {
            config.exposeIdsFor(TransactionEntity.class);
        });
    }
}