package dev.alexguesser.ride.application.usecase.input;

import java.util.UUID;

public record UpdatePositionInput(
        UUID rideId,
        int latitude,
        int longitude
) {
}
