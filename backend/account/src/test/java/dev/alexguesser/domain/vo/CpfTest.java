package dev.alexguesser.domain.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpfTest {

    @Test
    void validCpf() {
        assertDoesNotThrow(() -> new Cpf("123.456.789-09"));
    }

    @Test
    void invalidCpfNullValue() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Cpf(null));
        assertEquals("Invalid CPF", exception.getMessage());
    }

    @Test
    void invalidCpfAllDigitsSame() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Cpf("111.111.111-11"));
        assertEquals("Invalid CPF", exception.getMessage());
    }

    @Test
    void invalidCpfWrongCheckDigits() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Cpf("123.456.789-00"));
        assertEquals("Invalid CPF", exception.getMessage());
    }

    @Test
    void validCpfWithoutFormatting() {
        assertDoesNotThrow(() -> new Cpf("12345678909"));
    }

    @Test
    void invalidCpfWithNonDigitCharacters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Cpf("123.abc.789-09"));
        assertEquals("Invalid CPF", exception.getMessage());
    }
}