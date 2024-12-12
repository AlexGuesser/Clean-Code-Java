package dev.alexguesser.account.domain.vo;

import jakarta.xml.bind.DatatypeConverter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Password extends Password {
    public SHA1Password(String password, boolean newPassword) {
        super(password, PasswordType.SHA1, newPassword);
    }

    public SHA1Password(String password) {
        this(password, true);
    }

    @Override
    protected String encrypt(String password) {
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            return DatatypeConverter
                    .printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
