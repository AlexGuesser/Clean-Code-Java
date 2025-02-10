package dev.alexguesser.ride.domain.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CoordTest {

    @Test
    void shouldCreateCoordForValidParameters() {
        assertDoesNotThrow(() -> new Coord(0, 0));
    }

    @Test
    void shouldNotCreateCoordForInvalidLatitude() {
        assertThrows(IllegalArgumentException.class, () -> new Coord(-91, 0));
        assertThrows(IllegalArgumentException.class, () -> new Coord(91, 0));
    }

    @Test
    void shouldNotCreateCoordForInvalidLongitude() {
        assertThrows(IllegalArgumentException.class, () -> new Coord(0, -181));
        assertThrows(IllegalArgumentException.class, () -> new Coord(0, 181));
    }

}