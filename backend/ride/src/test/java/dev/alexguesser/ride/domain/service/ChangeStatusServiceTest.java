package dev.alexguesser.ride.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dev.alexguesser.ride.application.gateway.RideRepositoryGateway;
import dev.alexguesser.ride.application.usecase.AcceptRide;
import dev.alexguesser.ride.application.usecase.FinishRide;
import dev.alexguesser.ride.application.usecase.StartRide;
import dev.alexguesser.ride.application.usecase.input.AcceptRideInput;
import dev.alexguesser.ride.application.usecase.input.FinishRideInput;
import dev.alexguesser.ride.application.usecase.input.StartRideInput;
import dev.alexguesser.ride.domain.entity.Ride;
import jakarta.persistence.EntityNotFoundException;

class ChangeStatusServiceTest {

    @Mock
    private AcceptRide acceptRide;

    @Mock
    private StartRide startRide;

    @Mock
    private FinishRide finishRide;

    @Mock
    private RideRepositoryGateway rideRepositoryGateway;

    @InjectMocks
    private ChangeStatusService changeStatusService;

    private UUID rideId;
    private UUID driverId;
    private Ride ride;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rideId = UUID.randomUUID();
        driverId = UUID.randomUUID();
        ride = mock(Ride.class);
    }

    @Test
    void changeStatusToAccepted() {
        when(rideRepositoryGateway.getRideById(rideId)).thenReturn(Optional.of(ride));
        String result = changeStatusService.changeStatus("accepted", rideId, driverId);
        verify(acceptRide).execute(any(AcceptRideInput.class));
        assertEquals(String.format("Ride %s is now accepted", rideId), result);
    }

    @Test
    void changeStatusToInProgress() {
        when(rideRepositoryGateway.getRideById(rideId)).thenReturn(Optional.of(ride));
        String result = changeStatusService.changeStatus("inProgress", rideId, driverId);
        verify(startRide).execute(any(StartRideInput.class));
        assertEquals(String.format("Ride %s is now inProgress", rideId), result);
    }

    @Test
    void changeStatusToFinished() {
        when(rideRepositoryGateway.getRideById(rideId)).thenReturn(Optional.of(ride));
        String result = changeStatusService.changeStatus("finished", rideId, driverId);
        verify(finishRide).execute(any(FinishRideInput.class));
        assertEquals(String.format("Ride %s is now finished", rideId), result);
    }

    @Test
    void changeStatusThrowsEntityNotFoundException() {
        when(rideRepositoryGateway.getRideById(rideId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> changeStatusService.changeStatus("accepted", rideId, driverId));
    }

    @Test
    void changeStatusThrowsIllegalArgumentException() {
        when(rideRepositoryGateway.getRideById(rideId)).thenReturn(Optional.of(ride));
        assertThrows(IllegalArgumentException.class, () -> changeStatusService.changeStatus("INVALID_STATUS", rideId, driverId));
    }
}