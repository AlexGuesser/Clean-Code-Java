package dev.alexguesser.ride.infra.gateway;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.ride.application.gateway.RideRepositoryGateway;
import dev.alexguesser.ride.domain.entity.Ride;
import dev.alexguesser.ride.infra.persistence.RideRepository;

@Component
public class JpaRideRepositoryGateway implements RideRepositoryGateway {


    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private RideEntityMapper rideEntityMapper;


    @Override
    public Optional<Ride> getRideById(UUID uuid) {
        return rideRepository.findById(uuid).map(rideEntityMapper::toDomain);
    }

    @Override
    public void updateRide(Ride ride) {
        rideRepository.save(rideEntityMapper.toEntity(ride));
    }
}
