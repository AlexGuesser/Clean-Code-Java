package dev.alexguesser.ride.infra.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.ride.application.gateway.RideRepositoryGateway;
import dev.alexguesser.ride.infra.persistence.RideRepository;

@Component
public class JpaRideRepositoryGateway implements RideRepositoryGateway {


    @Autowired
    private RideRepository rideRepository;


}
