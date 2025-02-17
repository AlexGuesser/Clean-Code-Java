package dev.alexguesser.invoice.infra.queue.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import dev.alexguesser.invoice.application.usecase.GenerateInvoice;

@Component
public class GenerateInvoiceConsumer {

    @Autowired
    private GenerateInvoice generateInvoice;

    @RabbitListener(queues = {"generateInvoice"})
    public void generateInvoice(@Payload Message message) {
        System.out.println("Received message from generateInvoice queue: " + new String(message.getBody()));
        generateInvoice.execute(new String(message.getBody()));
    }


}
