package dev.alexguesser.ride.infra.queue;

import java.util.function.Function;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RabbitMQAdapter implements QueueGateway {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void publish(String exchange, Object data) {
        amqpTemplate.send(exchange, "", new Message(data.toString().getBytes()));
    }


    @Override
    public void consume(String queue, Function<Object, Void> callback) {
        Message message = amqpTemplate.receive(queue);
        if (message != null) {
            String messageContent = new String(message.getBody());
            Object input = parseJson(messageContent);
            callback.apply(input);
        }
    }

    private Object parseJson(String messageContent) {
        // Replace with your preferred JSON library
        // Example with Jackson:
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(messageContent, Object.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON message: " + messageContent, e);
        }
    }

}
