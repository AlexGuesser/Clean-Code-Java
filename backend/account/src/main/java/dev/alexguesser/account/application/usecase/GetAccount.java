package dev.alexguesser.account.application.usecase;

import dev.alexguesser.account.application.usecase.gateways.AccountRepositoryGateway;
import dev.alexguesser.account.domain.entity.Account;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetAccount {

    @Autowired
    private AccountRepositoryGateway accountGateway;

    public Account execute(UUID accountId) {
        return accountGateway.getAccountById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

}
