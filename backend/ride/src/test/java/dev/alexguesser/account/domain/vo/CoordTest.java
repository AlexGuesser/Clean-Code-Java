package dev.alexguesser.account.domain.vo;

import static org.junit.jupiter.api.Assertions.*;

import dev.alexguesser.ride.domain.vo.Coord;

class CoordTest {

    @org.junit.jupiter.api.Test
    void shouldCreateCoordForValidParameters() {
        assertDoesNotThrow(() -> new Coord(0, 0));
    }

    @org.junit.jupiter.api.Test
    void shouldNotCreateCoordForInvalidLatitude() {
        assertThrows(IllegalArgumentException.class, () -> new Coord(-91, 0));
        assertThrows(IllegalArgumentException.class, () -> new Coord(91, 0));
    }

    @org.junit.jupiter.api.Test
    void shouldNotCreateCoordForInvalidLongitude() {
        assertThrows(IllegalArgumentException.class, () -> new Coord(0, -181));
        assertThrows(IllegalArgumentException.class, () -> new Coord(0, 181));
    }

}