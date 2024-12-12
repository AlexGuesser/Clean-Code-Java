package dev.alexguesser.ride.domain.vo.rideStatus;

import dev.alexguesser.ride.domain.entity.Ride;

public class AcceptedStatus extends RideStatus {
    public AcceptedStatus(Ride ride) {
        super("accepted", ride);
    }

    @Override
    public void start() {
        getRide().setStatus(new InProgressStatus(getRide()));
    }
}
