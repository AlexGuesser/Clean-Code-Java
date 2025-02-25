package dev.alexguesser.query.infra.gateway;

import org.springframework.stereotype.Component;

import dev.alexguesser.query.domain.entity.Query;
import dev.alexguesser.query.infra.persistence.QueryEntity;

@Component
public class QueryEntityMapper {

    public QueryEntity toEntity(Query query) {
        return new QueryEntity(
                query.getRideId(),
                query.getPassengerName(),
                query.getDriverName(),
                query.getFare(),
                query.getDistance()
        );
    }

    public Query toDomain(QueryEntity queryEntity) {
        return new Query(
                queryEntity.getRideId(),
                queryEntity.getPassengerName(),
                queryEntity.getDriverName(),
                queryEntity.getFare(),
                queryEntity.getDistance()
        );
    }

}
