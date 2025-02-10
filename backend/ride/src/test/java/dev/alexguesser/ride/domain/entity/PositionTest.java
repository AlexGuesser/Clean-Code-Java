package dev.alexguesser.ride.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import dev.alexguesser.ride.domain.vo.Coord;

class PositionTest {

//    @Test
//    void createPositionWithValidData() {
//        UUID rideId = UUID.randomUUID();
//        Coord coord = new Coord(40.7128, -74.0060);
//        Position position = Position.create(rideId, coord);
//
//        assertNotNull(position.getPositionId());
//        assertEquals(rideId, position.getRideId());
//        assertEquals(coord, position.getCoord());
//        assertTrue(position.getCreatedAt() <= Instant.now().toEpochMilli());
//    }
//
//    @Test
//    void createPositionWithNullRideId() {
//        Coord coord = new Coord(40.7128, -74.0060);
//        assertThrows(NullPointerException.class, () -> Position.create(null, coord));
//    }
//
//    @Test
//    void createPositionWithNullCoord() {
//        UUID rideId = UUID.randomUUID();
//        assertThrows(NullPointerException.class, () -> Position.create(rideId, null));
//    }
//
//    @Test
//    void setCoordUpdatesCoord() {
//        UUID rideId = UUID.randomUUID();
//        Coord initialCoord = new Coord(40.7128, -74.0060);
//        Position position = Position.create(rideId, initialCoord);
//
//        Coord newCoord = new Coord(34.0522, -118.2437);
//        position.setCoord(newCoord);
//
//        assertEquals(newCoord, position.getCoord());
//    }
}