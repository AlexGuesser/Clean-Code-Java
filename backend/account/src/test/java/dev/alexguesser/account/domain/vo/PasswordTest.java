package dev.alexguesser.account.domain.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordTest {

    @Test
    void validPassword() {
        assertDoesNotThrow(() -> new TextPlainPassword("password123"));
    }

    @Test
    void invalidPasswordWithLessThan8Characters() {
        assertThrows(IllegalArgumentException.class, () -> new TextPlainPassword("pass"));
    }

    @Test
    void encryptPasswordCorrectly_whenUsingTextPlain() {
        TextPlainPassword password = new TextPlainPassword("password123");
        assert(password.getEncryptedPassword().equals("password123"));
    }

    @Test
    void encryptPasswordCorrectly_whenUsingMD5() {
        MD5Password password = new MD5Password("password123");
        assert(password.getEncryptedPassword().equals("482c811da5d5b4bc6d497ffa98491e38"));
    }

    @Test
    void encryptPasswordCorrectly_whenUsingSHA1() {
        SHA1Password password = new SHA1Password("password123");
        assert(password.getEncryptedPassword().equals("cbfdac6008f9cab4083784cbd1874f76618d2a97"));
    }


}