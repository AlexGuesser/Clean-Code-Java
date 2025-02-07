package dev.alexguesser.ride.application.usecase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.ride.application.gateway.PositionRepositoryGateway;
import dev.alexguesser.ride.application.gateway.RideRepositoryGateway;
import dev.alexguesser.ride.application.usecase.output.GetRideOutput;
import dev.alexguesser.ride.domain.DistanceCalculator;
import dev.alexguesser.ride.domain.entity.Position;
import dev.alexguesser.ride.domain.entity.Ride;
import jakarta.persistence.EntityNotFoundException;

@Component
public class GetRide {

    @Autowired
    private RideRepositoryGateway rideRepositoryGateway;

    @Autowired
    private PositionRepositoryGateway positionRepositoryGateway;

    public GetRideOutput execute(UUID rideId) {
        Optional<Ride> ride = rideRepositoryGateway.getRideById(rideId);
        if (ride.isEmpty()) {
            throw new EntityNotFoundException("Ride not found");
        }
        List<Position> positionsByRideId = positionRepositoryGateway.getPositionsByRideId(rideId);
        float distance = getDistance(ride.get(), positionsByRideId);
        return new GetRideOutput(
                ride.get().getRideId(),
                ride.get().getPassengerId(),
                ride.get().getFrom().latitude(),
                ride.get().getFrom().longitude(),
                ride.get().getTo().latitude(),
                ride.get().getTo().longitude(),
                ride.get().getStatus(),
                ride.get().getDriverId(),
                positionsByRideId,
                distance,
                ride.get().getFare()
        );

    }

    private float getDistance(Ride ride, List<Position> positions) {
        if (ride.getStatus().getValue().equals("finished")) {
            return ride.getDistance();
        }
        return DistanceCalculator.calculateByPositions(positions);
    }

}
