package dev.alexguesser.query.infra.gateway;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.query.application.gateway.QueryRepositoryGateway;
import dev.alexguesser.query.domain.entity.Query;
import dev.alexguesser.query.infra.persistence.QueryRepository;

@Component
public class JpaQueryRepositoryGateway implements QueryRepositoryGateway {

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private QueryEntityMapper queryEntityMapper;

    @Override
    public Query getQueryByRideId(UUID rideId) {
        return queryEntityMapper.toDomain(queryRepository.findByRideId(rideId));
    }

    @Override
    public void createNewQuery(Query query) {
        queryRepository.save(queryEntityMapper.toEntity(query));
    }

    @Override
    public void saveQuery(Query query) {
        queryRepository.save(queryEntityMapper.toEntity(query));
    }


}
