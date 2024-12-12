package dev.alexguesser.ride.domain.service;

import dev.alexguesser.ride.domain.entity.Position;
import dev.alexguesser.ride.domain.vo.Coord;

public class DistanceCalculator {

    public static float calculate(Coord from, Coord to) {
        int earthRadius = 6371;
        double degreesToRadians = Math.PI / 180;
        double deltaLat = (to.latitude() - from.latitude()) * degreesToRadians;
        double deltaLon = (to.longitude() - from.longitude()) * degreesToRadians;
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(from.latitude() * degreesToRadians) *
                        Math.cos(to.latitude() * degreesToRadians) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;
        return (float) Math.round(distance);
    }

    public static float calculateByPositions(Position[] positions) {
        float distance = 0;
        for (int i = 0; i < positions.length - 1; i++) {
            Position position = positions[i];
            Position nextPosition = positions[i + 1];
            distance += DistanceCalculator.calculate(position.getCoord(), nextPosition.getCoord());
        }
        return distance;
    }

}


//static calculate (from: Coord, to: Coord) {
//		const earthRadius = 6371;
//		const degreesToRadians = Math.PI / 180;
//		const deltaLat = (to.getLat() - from.getLat()) * degreesToRadians;
//		const deltaLon = (to.getLong() - from.getLong()) * degreesToRadians;
//		const a =
//            Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
//                    Math.cos(from.getLat() * degreesToRadians) *
//                            Math.cos(to.getLat() * degreesToRadians) *
//                            Math.sin(deltaLon / 2) *
//                            Math.sin(deltaLon / 2);
//		const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//		const distance = earthRadius * c;
//    return Math.round(distance);
//}
//
//static calculateByPositions (positions: Position[]) {
//    let distance = 0;
//    for (const [index, position] of positions.entries()) {
//			const nextPosition = positions[index + 1];
//        if (!nextPosition) continue;
//        distance += DistanceCalculator.calculate(position.coord, nextPosition.coord);
//    }
//    return distance;
//}
