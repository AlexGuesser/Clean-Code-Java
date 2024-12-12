package dev.alexguesser.account.domain.vo;

public record Cpf(String value) {

    public static final int FIRST_DIGIT_FACTOR = 10;
    public static final int SECOND_DIGIT_FACTOR = 11;
    public static final int CPF_VALID_LENGTH = 11;

    public Cpf {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid CPF");
        }
    }

    private boolean isValid(String value) {
        if (value == null) {
            return false;
        }
        value = value.replaceAll("[^0-9]", "");
        if (value.length() != CPF_VALID_LENGTH) {
            return false;
        }
        if (allDigitsTheSame(value)) {
            return false;
        }
        int digit1 = calculateDigit(value, FIRST_DIGIT_FACTOR);
        int digit2 = calculateDigit(value, SECOND_DIGIT_FACTOR);
        return value.endsWith(String.valueOf(digit1) + digit2);
    }

    public int calculateDigit(String cpf, int factor) {
        int total = 0;
        for (int i = 0; i < cpf.length(); i++) {
            char digit = cpf.charAt(i);
            if (factor > 1) {
                total += Character.getNumericValue(digit) * factor--;
            }
        }
        int remainder = total % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }

    private boolean allDigitsTheSame(String value) {
        char firstDigit = value.charAt(0);
        return value.chars().allMatch(ch -> ch == firstDigit);
    }

}
