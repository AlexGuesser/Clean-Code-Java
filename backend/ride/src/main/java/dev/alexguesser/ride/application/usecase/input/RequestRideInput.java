package dev.alexguesser.ride.application.usecase.input;

import java.util.UUID;

public record RequestRideInput(
        UUID passengerId,
        int fromLat,
        int fromLong,
        int toLat,
        int toLong
) {
}
