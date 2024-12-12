package dev.alexguesser.ride.domain.vo.rideStatus;

import dev.alexguesser.ride.domain.entity.Ride;

public class FinishedStatus extends RideStatus {
    public FinishedStatus(Ride ride) {
        super("finished", ride);
    }
}
