package dev.alexguesser.ride.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.account.application.usecase.gateways.AccountRepositoryGateway;
import dev.alexguesser.ride.application.gateway.RideRepositoryGateway;
import dev.alexguesser.ride.infra.queue.QueueGateway;

@Component
public class AcceptRide {

    @Autowired
    private AccountRepositoryGateway accountRepositoryGateway;

    @Autowired
    private RideRepositoryGateway rideRepositoryGateway;

    @Autowired
    private QueueGateway queue;


}
