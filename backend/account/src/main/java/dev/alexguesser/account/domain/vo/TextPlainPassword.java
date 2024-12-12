package dev.alexguesser.account.domain.vo;

public class TextPlainPassword extends Password {
    public TextPlainPassword(String password, boolean newPassword) {
        super(password, PasswordType.TEXT_PLAIN, newPassword);
    }

    public TextPlainPassword(String password) {
        this(password, true);
    }

    @Override
    protected String encrypt(String password) {
        return password;
    }
}
