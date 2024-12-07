package dev.alexguesser.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.alexguesser.domain.entity.Account;
import dev.alexguesser.domain.vo.PasswordType;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class SignupTest {

    @Autowired
    private Signup signup;

    @Autowired
    private GetAccount getAccount;

    @Test
    void testSignup() {
        Account account = signup.execute(new Signup.SignupInput(
                "John Doe", "john.doe@gmail.com", "97456321558", "", "12345678", PasswordType.TEXT_PLAIN, false, false));
        assertThat(account).isNotNull();
        assertThat(account.getName().value()).isEqualTo("John Doe");
        assertThat(account.getAccountId()).isNotNull();

        Account accountFromDatabase = getAccount.execute(account.getAccountId());
        assertThat(accountFromDatabase).isNotNull();
        assertThat(accountFromDatabase.getName().value()).isEqualTo("John Doe");
        assertThat(accountFromDatabase.getAccountId()).isEqualTo(account.getAccountId());
    }

}