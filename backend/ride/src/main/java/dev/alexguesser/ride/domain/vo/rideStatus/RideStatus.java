package dev.alexguesser.ride.domain.vo.rideStatus;

import dev.alexguesser.ride.domain.entity.Ride;

public abstract class RideStatus {

    private final String value;
    private final Ride ride;

    protected RideStatus(String value, Ride ride) {
        this.value = value;
        this.ride = ride;
    }

    public void request() {
        throw new IllegalStateException("Invalid status");
    }

    public void accept() {
        throw new IllegalStateException("Invalid status");
    }

    public void start() {
        throw new IllegalStateException("Invalid status");
    }

    public void finish() {
        throw new IllegalStateException("Invalid status");
    }

    protected Ride getRide() {
        return this.ride;
    }

    public String getValue() {
        return value;
    }

    public static class RideStatusFactory {
        public static RideStatus create(String status, Ride ride) {
            return switch (status) {
                case "requested" -> new RequestedStatus(ride);
                case "accepted" -> new AcceptedStatus(ride);
                case "inProgress" -> new InProgressStatus(ride);
                case "finished" -> new FinishedStatus(ride);
                default -> throw new IllegalArgumentException("Invalid status");
            };
        }
    }
}
