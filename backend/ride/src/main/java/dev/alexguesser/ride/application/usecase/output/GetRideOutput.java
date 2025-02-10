package dev.alexguesser.ride.application.usecase.output;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dev.alexguesser.ride.domain.entity.Position;
import dev.alexguesser.ride.domain.vo.rideStatus.RideStatus;

@JsonIgnoreProperties
public record GetRideOutput(
        UUID rideId,
        UUID passengerId,
        double fromLat,
        double fromLong,
        double toLat,
        double toLong,
        RideStatus status,
        UUID driverId,
        List<Position> positions,
        double distance,
        double fare,
        double straightDistance) {
}
