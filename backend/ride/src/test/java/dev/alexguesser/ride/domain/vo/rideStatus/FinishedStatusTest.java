package dev.alexguesser.ride.domain.vo.rideStatus;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import dev.alexguesser.ride.domain.entity.Ride;

class FinishedStatusTest {
    private final Ride ride = mock(Ride.class);

    @Test
    void requestThrowsIllegalStateException() {
        FinishedStatus finishedStatus = new FinishedStatus(ride);
        assertThrows(IllegalStateException.class, finishedStatus::request);
    }

    @Test
    void acceptThrowsIllegalStateException() {
        FinishedStatus finishedStatus = new FinishedStatus(ride);
        assertThrows(IllegalStateException.class, finishedStatus::accept);
    }

    @Test
    void startThrowsIllegalStateException() {
        FinishedStatus finishedStatus = new FinishedStatus(ride);
        assertThrows(IllegalStateException.class, finishedStatus::start);
    }

    @Test
    void finishThrowsIllegalStateException() {
        FinishedStatus finishedStatus = new FinishedStatus(ride);
        assertThrows(IllegalStateException.class, finishedStatus::finish);
    }
}