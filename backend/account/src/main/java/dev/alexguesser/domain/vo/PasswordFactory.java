package dev.alexguesser.domain.vo;

public class PasswordFactory {
    public static Password create(String password, PasswordType passwordType) {
        return switch (passwordType) {
            case TEXT_PLAIN -> new TextPlainPassword(password, true);
            case SHA1 -> new SHA1Password(password, true);
            case MD5 -> new MD5Password(password, true);
        };
    }

    public static Password restore(String encryptedPassword, PasswordType passwordType) {
        return switch (passwordType) {
            case TEXT_PLAIN -> new TextPlainPassword(encryptedPassword, false);
            case SHA1 -> new SHA1Password(encryptedPassword, false);
            case MD5 -> new MD5Password(encryptedPassword, false);
        };
    }
}
