package dev.alexguesser.ride.application.gateway;

import java.util.Optional;
import java.util.UUID;

import dev.alexguesser.ride.domain.entity.Ride;

public interface RideRepositoryGateway {
    Optional<Ride> getRideById(UUID uuid);

    void updateRide(Ride ride);
}
