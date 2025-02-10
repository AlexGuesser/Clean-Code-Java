package dev.alexguesser.ride.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import dev.alexguesser.ride.domain.entity.Position;
import dev.alexguesser.ride.domain.vo.Coord;

public class DistanceCalculator {

    public static double calculate(Coord from, Coord to) {
        int earthRadius = 6371;
        double degreesToRadians = Math.PI / 180;
        double deltaLat = (to.latitude() - from.latitude()) * degreesToRadians;
        double deltaLon = (to.longitude() - from.longitude()) * degreesToRadians;
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(from.latitude() * degreesToRadians) *
                        Math.cos(to.latitude() * degreesToRadians) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return new BigDecimal(earthRadius * c).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static double calculateByPositions(List<Position> positions) {
        double distance = 0;
        for (int i = 0; i < positions.size() - 1; i++) {
            Position position = positions.get(i);
            Position nextPosition = positions.get(i + 1);
            distance += DistanceCalculator.calculate(position.getCoord(), nextPosition.getCoord());
        }
        return distance;
    }

}
