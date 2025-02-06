package dev.alexguesser.ride.infra.configuration;

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
