package dev.alexguesser.common.messages;

import java.io.Serializable;

public record RideCompletedMessage(
        String rideId,
        double amount,
        double distance
) implements Serializable {

    public static String eventName = "rideCompleted";
}
