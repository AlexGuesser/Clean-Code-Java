package dev.alexguesser.application.usecase;

import dev.alexguesser.application.usecase.gateways.AccountRepositoryGateway;
import dev.alexguesser.domain.entity.Account;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetAccount {

    @Autowired
    private AccountRepositoryGateway accountRepository;

    public Account execute(UUID accountId) {
        return accountRepository.getAccountById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }
}