package dev.alexguesser.ride.infra.queue;

public enum Exchanges {

    RIDE_ACCEPTED("rideAccepted"), RIDE_COMPLETED("rideCompleted");

    private String name;

    Exchanges(String name) {
        this.name = name;
    }

}
