package dev.alexguesser.ride.infra.queue;

import java.util.function.Function;

public interface QueueGateway {

    void publish(String exchange, Object data);

    void consume(String queue, Function<Object, Void> callback);

}
