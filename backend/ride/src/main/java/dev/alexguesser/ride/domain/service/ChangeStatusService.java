package dev.alexguesser.ride.domain.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexguesser.ride.application.gateway.RideRepositoryGateway;
import dev.alexguesser.ride.application.usecase.AcceptRide;
import dev.alexguesser.ride.application.usecase.FinishRide;
import dev.alexguesser.ride.application.usecase.StartRide;
import dev.alexguesser.ride.application.usecase.input.AcceptRideInput;
import dev.alexguesser.ride.application.usecase.input.FinishRideInput;
import dev.alexguesser.ride.application.usecase.input.StartRideInput;
import dev.alexguesser.ride.domain.entity.Ride;
import dev.alexguesser.ride.domain.vo.rideStatus.AcceptedStatus;
import dev.alexguesser.ride.domain.vo.rideStatus.FinishedStatus;
import dev.alexguesser.ride.domain.vo.rideStatus.InProgressStatus;
import dev.alexguesser.ride.domain.vo.rideStatus.RideStatus;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ChangeStatusService {

    @Autowired
    private AcceptRide acceptRide;

    @Autowired
    private StartRide startRide;

    @Autowired
    private FinishRide finishRide;

    @Autowired
    private RideRepositoryGateway rideRepositoryGateway;


    public String changeStatus(String newStatus, UUID rideId, UUID driverId) {
        Ride ride = rideRepositoryGateway.getRideById(rideId)
                .orElseThrow(() -> new EntityNotFoundException("Ride not found"));
        RideStatus rideStatus = RideStatus.RideStatusFactory.create(newStatus, ride);
        if (rideStatus instanceof AcceptedStatus) {
            acceptRide.execute(new AcceptRideInput(rideId, driverId));
        } else if (rideStatus instanceof InProgressStatus) {
            startRide.execute(new StartRideInput(rideId));
        } else if (rideStatus instanceof FinishedStatus) {
            finishRide.execute(new FinishRideInput(rideId));
        } else {
            throw new IllegalArgumentException("Invalid status");
        }

        return String.format("Ride %s is now %s", rideId, newStatus);
    }

}
