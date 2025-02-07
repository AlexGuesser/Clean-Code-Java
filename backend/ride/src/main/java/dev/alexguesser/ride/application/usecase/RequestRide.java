package dev.alexguesser.ride.application.usecase;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.ride.application.gateway.RideRepositoryGateway;
import dev.alexguesser.ride.application.usecase.input.RequestRideInput;
import dev.alexguesser.ride.application.usecase.output.RequestRideOutput;
import dev.alexguesser.ride.domain.entity.Ride;
import dev.alexguesser.ride.domain.vo.Coord;
import dev.alexguesser.ride.infra.gateway.AccountGateway;
import dev.alexguesser.ride.infra.queue.Exchanges;
import dev.alexguesser.ride.infra.queue.QueueGateway;
import jakarta.persistence.EntityNotFoundException;

@Component
public class RequestRide {


    @Autowired
    private AccountGateway accountGateway;

    @Autowired
    private RideRepositoryGateway rideRepositoryGateway;

    @Autowired
    private QueueGateway queue;

    public RequestRideOutput execute(RequestRideInput input) {
        AccountGateway.AccountDetailDto account = accountGateway.getAccountById(input.passengerId().toString());
        if (account == null) {
            throw new EntityNotFoundException("Account not found");
        }
        if (!account.isPassenger()) {
            throw new IllegalArgumentException("Account must be from a passenger");
        }
        boolean hasActiveRideByPassengerId = rideRepositoryGateway.hasActiveRideByPassengerId(input.passengerId());
        if (hasActiveRideByPassengerId) {
            throw new IllegalArgumentException("Passenger already have an active ride");
        }
        Ride ride = createRide(input);
        rideRepositoryGateway.save(ride);
        queue.publish(
                Exchanges.RIDE_REQUESTED.name(),
                new RideRequestQueueObject(ride.getRideId(), account.name())

        );
        return new RequestRideOutput(ride.getRideId());
    }

    private Ride createRide(RequestRideInput input) {
        return Ride.create(
                input.passengerId(),
                Coord.create(input.fromLat(), input.fromLong()),
                Coord.create(input.toLat(), input.toLong())
        );
    }

    private record RideRequestQueueObject(
            UUID rideId, String passengerName
    ) {
    }

}


