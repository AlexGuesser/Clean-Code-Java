package dev.alexguesser.query.application.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.query.infra.gateway.AccountGateway;
import dev.alexguesser.query.infra.gateway.RideGateway;

@Component
public class GetRideComposition {

    @Autowired
    private AccountGateway accountGateway;

    @Autowired
    private RideGateway rideGateway;


    public GetRideCompositionOutput execute(UUID rideId) {
        RideGateway.GetRideDto ride = rideGateway.getRideById(rideId.toString());
        AccountGateway.AccountDetailDto passenger = accountGateway.getAccountById(ride.passengerId());
        AccountGateway.AccountDetailDto driver = null;
        if (ride.driverId() != null) {
            driver = accountGateway.getAccountById(ride.driverId());
        }
        return new GetRideCompositionOutput(
                ride.rideId(),
                passenger.name(),
                driver == null ? null : driver.name(),
                ride.fare(),
                ride.distance()
        );
    }

    public record GetRideCompositionOutput(
            String rideId,
            String passengerName,
            String driverName,
            double fare,
            double distance
    ) {
    }

}
