package dev.alexguesser.ride.application.usecase;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.ride.application.gateway.RideRepositoryGateway;
import dev.alexguesser.ride.application.usecase.input.AcceptRideInput;
import dev.alexguesser.ride.domain.entity.Ride;
import dev.alexguesser.ride.domain.event.RideAcceptedEvent;
import dev.alexguesser.ride.infra.gateway.AccountGateway;
import dev.alexguesser.ride.infra.queue.Exchanges;
import dev.alexguesser.ride.infra.queue.QueueGateway;
import jakarta.persistence.EntityNotFoundException;

@Component
public class AcceptRide {

    @Autowired
    private AccountGateway accountRepositoryGateway;

    @Autowired
    private RideRepositoryGateway rideRepositoryGateway;

    @Autowired
    private QueueGateway queue;

    public void execute(AcceptRideInput input) {
        AccountGateway.AccountDetailDto account = accountRepositoryGateway.getAccountById(input.rideId().toString());
        if (account == null) {
            throw new EntityNotFoundException("Account not found");
        }
        if (!account.isDriver()) {
            throw new IllegalArgumentException("Account is not a driver");
        }
        Optional<Ride> ride = rideRepositoryGateway.getRideById(input.rideId());
        if (ride.isEmpty()) {
            throw new EntityNotFoundException("Ride not found");
        }
        ride.get().accept(input.rideId());
        rideRepositoryGateway.updateRide(ride.get());
        queue.publish(
                Exchanges.RIDE_ACCEPTED.name(),
                new RideAcceptedEvent(
                        ride.get().getRideId(),
                        UUID.fromString(account.accountId())
                )
        );
    }


}
