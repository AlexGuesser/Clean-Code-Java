package dev.alexguesser.ride.application.usecase.input;

import java.util.UUID;

public record FinishRideInput(
        UUID rideId
) {
}
