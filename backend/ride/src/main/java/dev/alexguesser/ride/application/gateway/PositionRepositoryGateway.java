package dev.alexguesser.ride.application.gateway;

import java.util.List;
import java.util.UUID;

import dev.alexguesser.ride.domain.entity.Position;

public interface PositionRepositoryGateway {
    List<Position> getPositionsByRideId(UUID uuid);

}
