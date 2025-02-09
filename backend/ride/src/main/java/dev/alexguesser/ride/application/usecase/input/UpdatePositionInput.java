package dev.alexguesser.ride.application.usecase.input;

import java.util.UUID;

import dev.alexguesser.ride.infra.controller.dto.UpdatePositionDto;

public record UpdatePositionInput(
        UUID rideId,
        int latitude,
        int longitude
) {
    public static UpdatePositionInput fromDto(UpdatePositionDto dto) {
        return new UpdatePositionInput(
                dto.rideId(),
                dto.latitude(),
                dto.longitude()
        );
    }
}
