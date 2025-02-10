package dev.alexguesser.ride.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import dev.alexguesser.ride.domain.entity.Position;
import dev.alexguesser.ride.domain.vo.Coord;

class DistanceCalculatorTest {

    @Test
    void calculateReturnsCorrectDistance() {
        Coord from = new Coord(52.2296756, 21.0122287);
        Coord to = new Coord(41.8919300, 12.5113300);
        double distance = DistanceCalculator.calculate(from, to);
        assertEquals(1315.51, distance);
    }

    @Test
    void calculateReturnsZeroForSameCoordinates() {
        Coord from = new Coord(52.2296756, 21.0122287);
        Coord to = new Coord(52.2296756, 21.0122287);
        double distance = DistanceCalculator.calculate(from, to);
        assertEquals(0.0, distance);
    }

    @Test
    void calculateByPositionsReturnsCorrectDistance() {
        Position pos1 = Position.create(UUID.randomUUID(), new Coord(52.2296756, 21.0122287));
        Position pos2 = Position.create(UUID.randomUUID(), new Coord(41.8919300, 12.5113300));
        Position pos3 = Position.create(UUID.randomUUID(), new Coord(48.856614, 2.3522219));
        List<Position> positions = List.of(pos1, pos2, pos3);
        double distance = DistanceCalculator.calculateByPositions(positions);
        assertEquals(2422.52, distance);
    }

    @Test
    void calculateByPositionsReturnsZeroForSinglePosition() {
        Position pos1 = Position.create(UUID.randomUUID(), new Coord(52.2296756, 21.0122287));
        List<Position> positions = List.of(pos1);
        double distance = DistanceCalculator.calculateByPositions(positions);
        assertEquals(0.0, distance);
    }

    @Test
    void calculateByPositionsReturnsZeroForEmptyList() {
        List<Position> positions = List.of();
        double distance = DistanceCalculator.calculateByPositions(positions);
        assertEquals(0.0, distance);
    }
}