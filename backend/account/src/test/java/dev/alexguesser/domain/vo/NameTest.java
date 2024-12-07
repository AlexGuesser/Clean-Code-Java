package dev.alexguesser.domain.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void validNameSingleWord() {
        assertDoesNotThrow(() -> new Name("John"));
    }

    @Test
    void validNameMultipleWords() {
        assertDoesNotThrow(() -> new Name("John Doe"));
    }

    @Test
    void invalidNameWithNumbers() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Name("John123"));
        assertEquals("Invalid name", exception.getMessage());
    }

    @Test
    void invalidNameWithSpecialCharacters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Name("John@Doe"));
        assertEquals("Invalid name", exception.getMessage());
    }

    @Test
    void invalidNameWithLeadingSpace() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Name(" John"));
        assertEquals("Invalid name", exception.getMessage());
    }

    @Test
    void invalidNameWithTrailingSpace() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Name("John "));
        assertEquals("Invalid name", exception.getMessage());
    }

    @Test
    void invalidNameWithMultipleSpaces() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Name("John  Doe"));
        assertEquals("Invalid name", exception.getMessage());
    }

    @Test
    void invalidNameEmptyString() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Name(""));
        assertEquals("Invalid name", exception.getMessage());
    }

    @Test
    void invalidNameNullValue() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Name(null));
        assertEquals("Invalid name", exception.getMessage());
    }
}