package dev.alexguesser.query.application.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.query.application.gateway.QueryRepositoryGateway;
import dev.alexguesser.query.domain.entity.Query;

@Component
public class GetCQRS {

    @Autowired
    private QueryRepositoryGateway queryRepositoryGateway;

    public Query execute(UUID rideId) {
        return queryRepositoryGateway.getQueryByRideId(rideId);
    }

}
