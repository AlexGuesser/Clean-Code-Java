package dev.alexguesser.ride.domain.event;

import java.util.UUID;

public record RideCompletedEvent(
        UUID rideId,
        double amount,
        double distance
) {

    public static String eventName = "rideCompleted";
}
