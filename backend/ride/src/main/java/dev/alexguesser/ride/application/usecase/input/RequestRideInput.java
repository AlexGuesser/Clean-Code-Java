package dev.alexguesser.ride.application.usecase.input;

import java.util.UUID;

import dev.alexguesser.ride.infra.controller.dto.RequestRideDto;

public record RequestRideInput(
        UUID passengerId,
        int fromLat,
        int fromLong,
        int toLat,
        int toLong
) {
    public static RequestRideInput fromDto(RequestRideDto dto) {
        return new RequestRideInput(
                UUID.fromString(dto.passengerId()),
                dto.fromLat(),
                dto.fromLong(),
                dto.toLat(),
                dto.toLong()
        );
    }
}
