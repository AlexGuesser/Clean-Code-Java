package dev.alexguesser.ride.domain.event;

import java.util.UUID;

public record RideAcceptedEvent(
        UUID rideId,
        UUID driverId
) {
}
