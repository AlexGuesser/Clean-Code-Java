package dev.alexguesser.common.messages;

import java.io.Serializable;

public record RideAcceptedMessage(
        String rideId,
        String driverId,
        String driverName
) implements Serializable {
}