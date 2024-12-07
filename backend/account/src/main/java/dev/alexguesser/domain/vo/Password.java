package dev.alexguesser.domain.vo;

import org.apache.commons.lang3.StringUtils;

public abstract class Password {

    private final String encryptedPassword;
    private final PasswordType passwordType;

    public Password(String password, PasswordType passwordType) {
        this(password, passwordType, true);
    }

    public Password(String password, PasswordType passwordType, boolean newPassword) {
        if (StringUtils.length(password) < 8) {
            throw new IllegalArgumentException("Password must have at least 8 characters");
        }
        this.encryptedPassword = newPassword ? encrypt(password) : password;
        this.passwordType = passwordType;
    }


    protected abstract String encrypt(String password);

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public PasswordType getPasswordType() {
        return passwordType;
    }
}
