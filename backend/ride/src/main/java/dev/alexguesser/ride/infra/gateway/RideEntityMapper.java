package dev.alexguesser.ride.infra.gateway;

import org.springframework.stereotype.Component;

import dev.alexguesser.ride.domain.entity.Ride;
import dev.alexguesser.ride.domain.vo.Coord;
import dev.alexguesser.ride.infra.persistence.RideEntity;

@Component
public class RideEntityMapper {

    public Ride toDomain(RideEntity entity) {
        return new Ride(
                entity.getRideId(),
                entity.getPassengerId(),
                entity.getDriverId(),
                new Coord(entity.getFromLatitude(), entity.getFromLongitude()),
                new Coord(entity.getToLatitude(), entity.getToLongitude()),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }

    public RideEntity toEntity(Ride ride) {
        return new RideEntity(
                ride.getRideId(),
                ride.getPassengerId(),
                ride.getDriverId(),
                ride.getFrom().longitude(),
                ride.getFrom().latitude(),
                ride.getTo().longitude(),
                ride.getTo().latitude(),
                ride.getStatus().getValue(),
                ride.getCreatedAt()
        );
    }

}
