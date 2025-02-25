package dev.alexguesser.query.application.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.common.messages.RideRequestedMessage;
import dev.alexguesser.query.application.gateway.QueryRepositoryGateway;
import dev.alexguesser.query.domain.entity.Query;
import jakarta.transaction.Transactional;

@Component
public class RideRequestedUpdateProjection {

    @Autowired
    private QueryRepositoryGateway queryRepositoryGateway;

    @Transactional
    public void execute(RideRequestedMessage message) {
        queryRepositoryGateway.createNewQuery(new Query(UUID.fromString(message.rideId()), message.passengerName()));
    }

}
