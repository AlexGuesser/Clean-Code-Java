package dev.alexguesser.payment.infra.queue.consumer;

import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alexguesser.common.messages.RideCompletedMessage;
import dev.alexguesser.payment.application.usecase.ProcessPayment;
import dev.alexguesser.payment.application.usecase.input.ProcessPaymentInput;

@Component
public class ProcessPaymentConsumer {

    @Autowired
    private ProcessPayment processPayment;

    @RabbitListener(queues = {"processPayment"})
    public void processPayment(RideCompletedMessage message) {
        System.out.println("Received message from processPayment queue: " + message);
        processPayment.execute(new ProcessPaymentInput(UUID.fromString(message.rideId()), message.amount()));
    }

}
