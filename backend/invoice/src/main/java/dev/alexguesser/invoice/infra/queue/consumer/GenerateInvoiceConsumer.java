package dev.alexguesser.invoice.infra.queue.consumer;

import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import dev.alexguesser.common.messages.RideCompletedMessage;
import dev.alexguesser.invoice.application.usecase.GenerateInvoice;

@Component
public class GenerateInvoiceConsumer {

    @Autowired
    private GenerateInvoice generateInvoice;

    @RabbitListener(queues = {"generateInvoice"})
    public void generateInvoice(@Payload RideCompletedMessage message) {
        System.out.println("Received message from generateInvoice queue: " + message);
        generateInvoice.execute(new GenerateInvoice.GenerateInvoiceInput(UUID.fromString(message.rideId()), message.amount()));
    }


}
