package dev.alexguesser.ride.domain.vo.rideStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import dev.alexguesser.ride.domain.entity.Ride;

class AcceptedStatusTest {

    private final Ride ride = mock(Ride.class);

    @Test
    void startChangesStatusToInProgress() {
        AcceptedStatus acceptedStatus = new AcceptedStatus(ride);
        acceptedStatus.start();
        verify(ride).setStatus(any(InProgressStatus.class));
    }

    @Test
    void startDoesNotThrowException() {
        AcceptedStatus acceptedStatus = new AcceptedStatus(ride);
        assertDoesNotThrow(acceptedStatus::start);
    }

    @Test
    void requestThrowsIllegalStateException() {
        AcceptedStatus acceptedStatus = new AcceptedStatus(ride);
        assertThrows(IllegalStateException.class, acceptedStatus::request);
    }

    @Test
    void acceptThrowsIllegalStateException() {
        AcceptedStatus acceptedStatus = new AcceptedStatus(ride);
        assertThrows(IllegalStateException.class, acceptedStatus::accept);
    }

    @Test
    void finishThrowsIllegalStateException() {
        AcceptedStatus acceptedStatus = new AcceptedStatus(ride);
        assertThrows(IllegalStateException.class, acceptedStatus::finish);
    }
}