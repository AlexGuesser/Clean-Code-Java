package dev.alexguesser.query.application.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.common.messages.RideAcceptedMessage;
import dev.alexguesser.query.application.gateway.QueryRepositoryGateway;
import dev.alexguesser.query.domain.entity.Query;
import jakarta.transaction.Transactional;

@Component
public class RideAcceptedUpdateProjection {

    @Autowired
    private QueryRepositoryGateway queryRepositoryGateway;

    @Transactional
    public void execute(RideAcceptedMessage message) {
        Query query = queryRepositoryGateway.getQueryByRideId(UUID.fromString(message.rideId()));
        query.setDriverName(message.driverName());
        queryRepositoryGateway.saveQuery(query);
    }

}
