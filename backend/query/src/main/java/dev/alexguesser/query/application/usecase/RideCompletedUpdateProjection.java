package dev.alexguesser.query.application.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.common.messages.RideCompletedMessage;
import dev.alexguesser.query.application.gateway.QueryRepositoryGateway;
import dev.alexguesser.query.domain.entity.Query;
import jakarta.transaction.Transactional;

@Component
public class RideCompletedUpdateProjection {

    @Autowired
    private QueryRepositoryGateway queryRepositoryGateway;

    public void execute(RideCompletedMessage message) {
        Query query = queryRepositoryGateway.getQueryByRideId(UUID.fromString(message.rideId()));
        query.setDistance(message.distance());
        query.setFare(message.amount());
        queryRepositoryGateway.saveQuery(query);
    }

}
