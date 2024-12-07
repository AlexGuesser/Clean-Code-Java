package dev.alexguesser.infra.gateways;

import dev.alexguesser.domain.entity.Account;
import dev.alexguesser.domain.vo.CarPlate;
import dev.alexguesser.infra.persistence.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountEntityMapper {
    public Account toDomain(AccountEntity accountEntity) {
        return new Account(
                accountEntity.getAccountId(),
                accountEntity.getName(),
                accountEntity.getEmail(),
                accountEntity.getCpf(),
                accountEntity.getCarPlate(),
                accountEntity.getPassword(),
                accountEntity.getPasswordType(),
                accountEntity.isPassenger(),
                accountEntity.isDriver()
        );
    }

    public AccountEntity toEntity(Account account) {
        return new AccountEntity(
                account.getAccountId(),
                account.getName().value(),
                account.getEmail().value(),
                account.getCpf().value(),
                account.getCarPlate().map(CarPlate::value).orElse(null),
                account.getPassword().getEncryptedPassword(),
                account.getPassword().getPasswordType(),
                account.isPassenger(),
                account.isDriver()
        );
    }
}
