package dev.alexguesser.ride.application.usecase.input;

import java.util.UUID;

public record AcceptRideInput(
        UUID rideId,
        UUID driverId
) {
}
