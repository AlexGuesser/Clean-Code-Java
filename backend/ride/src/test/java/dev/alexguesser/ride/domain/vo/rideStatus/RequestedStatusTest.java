package dev.alexguesser.ride.domain.vo.rideStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import dev.alexguesser.ride.domain.entity.Ride;

class RequestedStatusTest {

    private final Ride ride = mock(Ride.class);

    @Test
    void acceptChangesStatusToAccepted() {
        RequestedStatus requestedStatus = new RequestedStatus(ride);
        requestedStatus.accept();
        verify(ride).setStatus(any(AcceptedStatus.class));
    }

    @Test
    void acceptDoesNotThrowException() {
        RequestedStatus requestedStatus = new RequestedStatus(ride);
        assertDoesNotThrow(requestedStatus::accept);
    }

    @Test
    void requestThrowsIllegalStateException() {
        RequestedStatus requestedStatus = new RequestedStatus(ride);
        assertThrows(IllegalStateException.class, requestedStatus::request);
    }

    @Test
    void startThrowsIllegalStateException() {
        RequestedStatus requestedStatus = new RequestedStatus(ride);
        assertThrows(IllegalStateException.class, requestedStatus::start);
    }

    @Test
    void finishThrowsIllegalStateException() {
        RequestedStatus requestedStatus = new RequestedStatus(ride);
        assertThrows(IllegalStateException.class, requestedStatus::finish);
    }

}