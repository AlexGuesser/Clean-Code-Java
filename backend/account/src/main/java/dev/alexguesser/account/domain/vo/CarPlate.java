package dev.alexguesser.account.domain.vo;

import java.util.regex.Pattern;

public record CarPlate(String value) {

    private static final Pattern CAR_PLATE_PATTERN = Pattern.compile("[A-Z]{3}[0-9]{4}");

    public CarPlate {
        if (value == null || !CAR_PLATE_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid car plate");
        }
    }
}
