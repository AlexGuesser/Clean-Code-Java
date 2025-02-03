package dev.alexguesser.ride.application.usecase.output;

import java.util.List;
import java.util.UUID;

import dev.alexguesser.ride.domain.entity.Position;
import dev.alexguesser.ride.domain.vo.rideStatus.RideStatus;

public record GetRideOutput(
        UUID rideId,
        UUID passengerId,
        int fromLat,
        int fromLong,
        int toLat,
        int toLong,
        RideStatus status,
        UUID driverId,
        List<Position> positions,
        float distance,
        float fare
) {
}
