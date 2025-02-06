package dev.alexguesser.ride.infra.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import dev.alexguesser.account.infra.controller.dto.AccountDetailDto;

@Component
public class AccountGateway {

    @Autowired
    private RestClient restClient;

    public AccountDetailDto getAccountById(String id) {
        return
                restClient.get()
                        .uri("localhost:8080/account/" + id)
                        .retrieve()
                        .body(AccountDetailDto.class);
    }

}
