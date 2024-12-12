package dev.alexguesser.account.domain.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarPlateTest {

    @Test
    void validCarPlate() {
        assertDoesNotThrow(() -> new CarPlate("ABC1234"));
    }

    @Test
    void invalidCarPlateTooShort() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CarPlate("AB1234"));
        assertEquals("Invalid car plate", exception.getMessage());
    }

    @Test
    void invalidCarPlateTooLong() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CarPlate("ABCD1234"));
        assertEquals("Invalid car plate", exception.getMessage());
    }

    @Test
    void invalidCarPlateWrongFormat() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CarPlate("123ABCD"));
        assertEquals("Invalid car plate", exception.getMessage());
    }

    @Test
    void invalidCarPlateLowerCaseLetters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CarPlate("abc1234"));
        assertEquals("Invalid car plate", exception.getMessage());
    }
}