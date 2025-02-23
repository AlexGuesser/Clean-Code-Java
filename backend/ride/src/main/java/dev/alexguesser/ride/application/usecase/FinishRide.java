package dev.alexguesser.ride.application.usecase;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.common.messages.RideCompletedMessage;
import dev.alexguesser.ride.application.gateway.PositionRepositoryGateway;
import dev.alexguesser.ride.application.gateway.RideRepositoryGateway;
import dev.alexguesser.ride.application.usecase.input.FinishRideInput;
import dev.alexguesser.ride.domain.entity.Position;
import dev.alexguesser.ride.domain.entity.Ride;
import dev.alexguesser.ride.domain.event.RideCompletedEvent;
import dev.alexguesser.ride.infra.queue.Exchanges;
import dev.alexguesser.ride.infra.queue.QueueGateway;
import jakarta.persistence.EntityNotFoundException;

@Component
public class FinishRide {

    @Autowired
    RideRepositoryGateway rideRepositoryGateway;

    @Autowired
    PositionRepositoryGateway positionRepositoryGateway;

    @Autowired
    QueueGateway queue;

    public void execute(FinishRideInput input) {
        Optional<Ride> rideOptional = rideRepositoryGateway.getRideById(input.rideId());
        if (rideOptional.isEmpty()) {
            throw new EntityNotFoundException("Ride not found");
        }
        Ride ride = rideOptional.get();
        ride.register(
                RideCompletedEvent.eventName,
                (event) -> {
                    rideRepositoryGateway.updateRide(ride);
                    RideCompletedEvent rideCompletedEvent = (RideCompletedEvent) event;
                    queue.publish(
                            Exchanges.RIDE_COMPLETED.getName(),
                            new RideCompletedMessage(rideCompletedEvent.rideId(), rideCompletedEvent.amount(), rideCompletedEvent.distance())
                    );
                    return null;
                }
        );
        List<Position> positions = positionRepositoryGateway.getPositionsByRideId(input.rideId());
        ride.finish(positions);
    }

}
