package dev.alexguesser.ride.infra.queue;

import java.util.function.Function;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQAdapter implements QueueGateway {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void publish(String exchange, Object data) {
        amqpTemplate.send(exchange, "", new Message(data.toString().getBytes()));
    }

    @Override
    public void consume(String queue, Function callback) {

    }

}
