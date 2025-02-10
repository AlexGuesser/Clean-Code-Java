package dev.alexguesser.ride.domain.vo.rideStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import dev.alexguesser.ride.domain.entity.Ride;

class InProgressStatusTest {

    private final Ride ride = mock(Ride.class);

    @Test
    void finishChangesStatusToFinished() {
        InProgressStatus inProgressStatus = new InProgressStatus(ride);
        inProgressStatus.finish();
        verify(ride).setStatus(any(FinishedStatus.class));
    }

    @Test
    void finishDoesNotThrowException() {
        InProgressStatus inProgressStatus = new InProgressStatus(ride);
        assertDoesNotThrow(inProgressStatus::finish);
    }

    @Test
    void requestThrowsIllegalStateException() {
        InProgressStatus inProgressStatus = new InProgressStatus(ride);
        assertThrows(IllegalStateException.class, inProgressStatus::request);
    }

    @Test
    void acceptThrowsIllegalStateException() {
        InProgressStatus inProgressStatus = new InProgressStatus(ride);
        assertThrows(IllegalStateException.class, inProgressStatus::accept);
    }

    @Test
    void startThrowsIllegalStateException() {
        InProgressStatus inProgressStatus = new InProgressStatus(ride);
        assertThrows(IllegalStateException.class, inProgressStatus::start);
    }

}