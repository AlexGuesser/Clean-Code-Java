package dev.alexguesser.ride.infra.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestRideDto(
        String passengerId,
        int fromLat,
        int fromLong,
        int toLat,
        int toLong
) {
}
