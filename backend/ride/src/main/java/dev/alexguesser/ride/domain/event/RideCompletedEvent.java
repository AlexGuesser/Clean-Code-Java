package dev.alexguesser.ride.domain.event;

import java.io.Serializable;

public record RideCompletedEvent(String rideId, double amount, double distance) implements Serializable {

    public static String eventName = "rideCompleted";

    public static String getEventName() {
        return eventName;
    }
}
