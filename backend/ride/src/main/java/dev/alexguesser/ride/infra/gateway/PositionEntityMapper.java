package dev.alexguesser.ride.infra.gateway;

import org.springframework.stereotype.Component;

import dev.alexguesser.ride.domain.entity.Position;
import dev.alexguesser.ride.domain.vo.Coord;
import dev.alexguesser.ride.infra.persistence.PositionEntity;

@Component
public class PositionEntityMapper {

    public Position toDomain(PositionEntity positionEntity) {
        return new Position(
                positionEntity.getPositionId(),
                positionEntity.getRideId(),
                new Coord(positionEntity.getLatitude(), positionEntity.getLongitude()),
                positionEntity.getCreatedAt()
        );
    }

    public PositionEntity toEntity(Position position) {
        return new PositionEntity(
                position.getPositionId(),
                position.getRideId(),
                position.getCoord().longitude(),
                position.getCoord().latitude(),
                position.getCreatedAt()
        );
    }
}
