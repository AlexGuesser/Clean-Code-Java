package dev.alexguesser.account.domain.vo;

import java.util.regex.Pattern;

public record Name(String value) {

    static Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]+( [a-zA-Z]+)*$");

    public Name {
        if (value == null || !NAME_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid name");
        }
    }

}
