package dev.alexguesser.ride.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.ride.application.gateway.PositionRepositoryGateway;
import dev.alexguesser.ride.application.gateway.RideRepositoryGateway;
import dev.alexguesser.ride.application.usecase.input.UpdatePositionInput;
import dev.alexguesser.ride.domain.entity.Position;
import dev.alexguesser.ride.domain.vo.Coord;
import jakarta.persistence.EntityNotFoundException;

@Component
public class UpdatePosition {

    @Autowired
    private RideRepositoryGateway rideRepositoryGateway;

    @Autowired
    private PositionRepositoryGateway positionRepositoryGateway;

    public void execute(UpdatePositionInput input) {
        rideRepositoryGateway.getRideById(input.rideId())
                .orElseThrow(() -> new EntityNotFoundException("Ride not found"));
        Position position = Position.create(input.rideId(), new Coord(input.latitude(), input.longitude()));
        positionRepositoryGateway.save(position);
    }
}
