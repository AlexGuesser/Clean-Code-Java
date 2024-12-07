package dev.alexguesser.application.usecase.gateways;

import dev.alexguesser.domain.entity.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepositoryGateway {

    Optional<Account> getAccountById(UUID accountId);
    void saveAccount(Account account);

}
