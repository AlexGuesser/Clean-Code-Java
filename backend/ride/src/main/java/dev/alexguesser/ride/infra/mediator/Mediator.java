package dev.alexguesser.ride.infra.mediator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class Mediator {

    List<Handler> handlers = new ArrayList<>();

    public void register(String event, Function<Object, Void> callback) {
        handlers.add(new Handler(event, callback));
    }

    public void notify(String event, Object data) {
        handlers.stream()
                .filter(handler -> handler.event().equals(event))
                .forEach(handler -> handler.callback().apply(data));
    }

    private record Handler(
            String event,
            Function<Object, Void> callback
    ) {
    }

}
