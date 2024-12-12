package dev.alexguesser.ride.domain.vo.rideStatus;

import dev.alexguesser.ride.domain.entity.Ride;

public class RequestedStatus extends RideStatus {
    protected RequestedStatus(Ride ride) {
        super("requested", ride);
    }

    @Override
    public void accept() {
        this.getRide().setStatus(new AcceptedStatus(this.getRide()));
    }
}
