package dev.alexguesser.ride.infra.gateway;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.ride.application.gateway.PositionRepositoryGateway;
import dev.alexguesser.ride.domain.entity.Position;
import dev.alexguesser.ride.infra.persistence.PositionRepository;

@Component
public class JpaPositionRepositoryGateway implements PositionRepositoryGateway {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PositionEntityMapper positionEntityMapper;

    @Override
    public List<Position> getPositionsByRideId(UUID uuid) {
        return positionRepository.findByRideId(uuid)
                .stream()
                .map(positionEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void save(Position position) {
        positionRepository.save(positionEntityMapper.toEntity(position));
    }
}
