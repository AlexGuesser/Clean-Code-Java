package dev.alexguesser.ride.domain.vo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.xml.bind.DatatypeConverter;

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
