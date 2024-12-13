package dev.alexguesser.ride.domain;

import java.util.List;

import dev.alexguesser.ride.domain.entity.Position;
import dev.alexguesser.ride.domain.vo.Coord;

public class DistanceCalculator {
    public static float calculate(Coord from, Coord to) {
        final double earthRadius = 6371;
        final double degreesToRadians = Math.PI / 180;
        double deltaLat = (to.latitude() - from.latitude()) * degreesToRadians;
        double deltaLon = (to.longitude() - from.longitude()) * degreesToRadians;
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(from.latitude() * degreesToRadians) *
                        Math.cos(to.latitude() * degreesToRadians) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;
        return Math.round(distance);
    }

    public static float calculateByPositions(List<Position> positions) {
        float distance = 0;
        for (int i = 0; i < positions.size() - 1; i++) {
            Position position = positions.get(i);
            Position nextPosition = positions.get(i + 1);
            distance += DistanceCalculator.calculate(position.getCoord(), nextPosition.getCoord());
        }
        return distance;
    }
}