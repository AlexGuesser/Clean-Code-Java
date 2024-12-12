package dev.alexguesser.ride.domain.vo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.xml.bind.DatatypeConverter;

public class MD5Password extends Password {
    public MD5Password(String password, boolean newPassword) {
        super(password, PasswordType.MD5, newPassword);
    }

    public MD5Password(String password) {
        this(password, true);
    }

    @Override
    protected String encrypt(String password) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            return DatatypeConverter
                    .printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
