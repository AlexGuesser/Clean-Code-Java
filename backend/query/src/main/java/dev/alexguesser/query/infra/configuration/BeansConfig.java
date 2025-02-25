package dev.alexguesser.query.infra.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class BeansConfig {


    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }

}
