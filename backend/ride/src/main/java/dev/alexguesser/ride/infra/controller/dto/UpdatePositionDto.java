package dev.alexguesser.ride.infra.controller.dto;

import java.util.UUID;

public record UpdatePositionDto(
        UUID rideId,
        int latitude,
        int longitude
) {
}
