package dev.alexguesser.domain.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void validEmail() {
        assertDoesNotThrow(() -> new Email("test@example.com"));
    }

    @Test
    void invalidEmailWithoutAtSymbol() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Email("testexample.com"));
        assertEquals("Invalid email", exception.getMessage());
    }

    @Test
    void invalidEmailWithoutDomain() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Email("test@.com"));
        assertEquals("Invalid email", exception.getMessage());
    }

    @Test
    void invalidEmailWithSpecialCharacters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Email("test@exa!mple.com"));
        assertEquals("Invalid email", exception.getMessage());
    }

    @Test
    void validEmailWithSubdomain() {
        assertDoesNotThrow(() -> new Email("test@mail.example.com"));
    }

    @Test
    void invalidEmailWithMultipleAtSymbols() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Email("test@@example.com"));
        assertEquals("Invalid email", exception.getMessage());
    }

    @Test
    void invalidEmailWithSpaces() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Email("test @example.com"));
        assertEquals("Invalid email", exception.getMessage());
    }
}