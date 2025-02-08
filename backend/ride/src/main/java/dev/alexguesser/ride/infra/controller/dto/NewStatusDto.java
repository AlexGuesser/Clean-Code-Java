package dev.alexguesser.ride.infra.controller.dto;

import java.util.UUID;

public record NewStatusDto(
        String newStatus,
        UUID driverId
) {
}
