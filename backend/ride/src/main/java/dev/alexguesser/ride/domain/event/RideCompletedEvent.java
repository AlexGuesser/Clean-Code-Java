package dev.alexguesser.ride.domain.event;

import java.util.UUID;

public record RideCompletedEvent(
        UUID rideId,
        float amount,
        float distance
) {

    public static String eventName = "rideCompleted";
}
