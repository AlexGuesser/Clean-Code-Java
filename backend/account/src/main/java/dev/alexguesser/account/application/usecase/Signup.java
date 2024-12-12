package dev.alexguesser.account.application.usecase;

import dev.alexguesser.account.application.usecase.gateways.AccountRepositoryGateway;
import dev.alexguesser.account.domain.entity.Account;
import dev.alexguesser.account.domain.vo.PasswordType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Signup {

    @Autowired
    private AccountRepositoryGateway accountRepositoryGateway;

    public Account execute(SignupInput input) {
        Account account = Account.createAccount(
                input.name(),
                input.email(),
                input.cpf(),
                input.carPlate(),
                input.password(),
                input.passwordType(),
                input.isPassenger(),
                input.isDriver()
        );
        accountRepositoryGateway.saveAccount(account);
        return account;
    }

    public record SignupInput(
            String name,
            String email,
            String cpf,
            String carPlate,
            String password,
            PasswordType passwordType,
            boolean isPassenger,
            boolean isDriver
    ) {
    }

}


