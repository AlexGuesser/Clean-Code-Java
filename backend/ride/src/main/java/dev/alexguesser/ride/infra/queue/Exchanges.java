package dev.alexguesser.ride.infra.queue;

public enum Exchanges {

    RIDE_ACCEPTED("rideAccepted"), RIDE_COMPLETED("rideCompleted"), RIDE_REQUESTED("rideRequested");

    private String name;

    Exchanges(String name) {
        this.name = name;
    }

}
