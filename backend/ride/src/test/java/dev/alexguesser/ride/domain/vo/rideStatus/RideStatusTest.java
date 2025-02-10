package dev.alexguesser.ride.domain.vo.rideStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import dev.alexguesser.ride.domain.entity.Ride;

class RideStatusTest {

    private final Ride ride = mock(Ride.class);

    @Test
    void createRequestedStatus() {
        RideStatus status = RideStatus.RideStatusFactory.create("requested", ride);
        assertInstanceOf(RequestedStatus.class, status);
    }

    @Test
    void createAcceptedStatus() {
        RideStatus status = RideStatus.RideStatusFactory.create("accepted", ride);
        assertInstanceOf(AcceptedStatus.class, status);
    }

    @Test
    void createInProgressStatus() {
        RideStatus status = RideStatus.RideStatusFactory.create("inProgress", ride);
        assertInstanceOf(InProgressStatus.class, status);
    }

    @Test
    void createFinishedStatus() {
        RideStatus status = RideStatus.RideStatusFactory.create("finished", ride);
        assertInstanceOf(FinishedStatus.class, status);
    }

    @Test
    void createInvalidStatusThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> RideStatus.RideStatusFactory.create("invalid", ride));
    }

}