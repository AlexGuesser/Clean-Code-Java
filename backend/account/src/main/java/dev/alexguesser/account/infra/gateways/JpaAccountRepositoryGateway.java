package dev.alexguesser.account.infra.gateways;

import dev.alexguesser.account.application.usecase.gateways.AccountRepositoryGateway;
import dev.alexguesser.account.domain.entity.Account;
import dev.alexguesser.account.infra.persistence.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class JpaAccountRepositoryGateway implements AccountRepositoryGateway {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountEntityMapper accountEntityMapper;

    @Override
    public Optional<Account> getAccountById(UUID accountId) {
        return accountRepository.findById(accountId)
                .map(accountEntityMapper::toDomain);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(accountEntityMapper.toEntity(account));
    }
}
