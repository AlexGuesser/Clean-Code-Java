package dev.alexguesser.ride.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.ride.application.gateway.RideRepositoryGateway;
import dev.alexguesser.ride.application.usecase.input.StartRideInput;
import dev.alexguesser.ride.domain.entity.Ride;
import jakarta.persistence.EntityNotFoundException;

@Component
public class StartRide {

    @Autowired
    private RideRepositoryGateway rideRepositoryGateway;

    public void execute(StartRideInput input) {
        Ride ride = rideRepositoryGateway.getRideById(input.rideId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        ride.start();
        rideRepositoryGateway.save(ride);
    }
}
