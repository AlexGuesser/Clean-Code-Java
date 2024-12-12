package dev.alexguesser.account.domain.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import dev.alexguesser.account.domain.vo.PasswordType;

class AccountTest {

    @Test
    void shouldCreateAccountForValidParameters() {
        assertDoesNotThrow(() ->
                Account.createAccount(
                        "John Doe", "john.doe@gmail.com", "97456321558", "", "12345678", PasswordType.TEXT_PLAIN, false, false));
    }

    @Test
    void shouldCreateAccountForValidParametersWithCarPlate() {
        assertDoesNotThrow(() ->
                Account.createAccount(
                        "John Doe", "john.doe@gmail.com", "97456321558", "ABC1234", "12345678", PasswordType.TEXT_PLAIN, false, true));
    }

    @Test
    void shouldNotCreateAccountForInvalidName() {
        assertThatThrownBy(() -> Account.createAccount(
                "John1", "john.doe@gmail.com", "97456321558", "ABC1234", "12345678", PasswordType.TEXT_PLAIN, false, true))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid name");
    }

    @Test
    void shouldNotCreateAccountForInvalidEmail() {
        assertThatThrownBy(() -> Account.createAccount(
                "John Doe", "john.doe", "97456321558", "ABC1234", "12345678", PasswordType.TEXT_PLAIN, false, true))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid email");
    }

    @Test
    void shouldNotCreateAccountForInvalidCpf() {
        assertThatThrownBy(() -> Account.createAccount(
                "John Doe", "john.doe@gmail.com", "9745632155", "ABC1234", "12345678", PasswordType.TEXT_PLAIN, false, true))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid CPF");
    }

    @Test
    void shouldNotCreateAccountForInvalidCarPlate() {
        assertThatThrownBy(() -> Account.createAccount(
                "John Doe", "john.doe@gmail.com", "97456321558", "ABC123", "12345678", PasswordType.TEXT_PLAIN, false, true))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid car plate");
    }
}