package dev.alexguesser.query.application.gateway;

import java.util.UUID;

import dev.alexguesser.query.domain.entity.Query;


public interface QueryRepositoryGateway {

    Query getQueryByRideId(UUID rideId);

    void createNewQuery(Query query);

    void saveQuery(Query query);

}
