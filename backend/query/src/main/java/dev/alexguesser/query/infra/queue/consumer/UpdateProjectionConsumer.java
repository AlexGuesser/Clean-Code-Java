package dev.alexguesser.query.infra.queue.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import dev.alexguesser.common.messages.RideAcceptedMessage;
import dev.alexguesser.common.messages.RideCompletedMessage;
import dev.alexguesser.common.messages.RideRequestedMessage;
import dev.alexguesser.query.application.usecase.RideAcceptedUpdateProjection;
import dev.alexguesser.query.application.usecase.RideCompletedUpdateProjection;
import dev.alexguesser.query.application.usecase.RideRequestedUpdateProjection;

@Component
@RabbitListener(queues = {"updateProjection"})
public class UpdateProjectionConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateProjectionConsumer.class);

    @Autowired
    private RideRequestedUpdateProjection rideRequestedUpdateProjection;

    @Autowired
    private RideAcceptedUpdateProjection rideAcceptedUpdateProjection;

    @Autowired
    private RideCompletedUpdateProjection rideCompletedUpdateProjection;

    @RabbitHandler
    public void consumeFromRideRequested(@Payload RideRequestedMessage message) {
        LOGGER.info("Consuming message from ride requested");
        rideRequestedUpdateProjection.execute(message);
    }

    @RabbitHandler
    public void consumeFromRideAccepted(@Payload RideAcceptedMessage message) {
        LOGGER.info("Consuming message from ride accepted");
        rideAcceptedUpdateProjection.execute(message);
    }

    @RabbitHandler
    public void consumeFromRideCompleted(@Payload RideCompletedMessage message) {
        LOGGER.info("Consuming message from ride completed");
        rideCompletedUpdateProjection.execute(message);
    }

}
