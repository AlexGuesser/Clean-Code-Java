package dev.alexguesser.ride.infra.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;

@Component
public class AccountGateway {

    @Autowired
    private RestClient restClient;

    public AccountDetailDto getAccountById(String id) {
        return
                restClient.get()
                        .uri("http://localhost:8080/account/" + id)
                        .retrieve()
                        .body(AccountDetailDto.class);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record AccountDetailDto(
            String accountId,
            String name,
            String email,
            String cpf,
            @Nullable String carPlate,
            @JsonProperty("isPassenger")
            boolean isPassenger,
            @JsonProperty("isDriver")
            boolean isDriver
    ) {
    }

}

