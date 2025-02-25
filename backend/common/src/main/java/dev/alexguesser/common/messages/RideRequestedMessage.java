package dev.alexguesser.common.messages;

import java.io.Serializable;

public record RideRequestedMessage(
        String rideId, String passengerName
) implements Serializable {
}