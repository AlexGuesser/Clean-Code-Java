package dev.alexguesser.ride.domain.vo.rideStatus;

import dev.alexguesser.ride.domain.entity.Ride;

public class InProgressStatus extends RideStatus {
    public InProgressStatus(Ride ride) {
        super("inProgress", ride);
    }

    @Override
    public void finish() {
        getRide().setStatus(new FinishedStatus(getRide()));
    }
}
