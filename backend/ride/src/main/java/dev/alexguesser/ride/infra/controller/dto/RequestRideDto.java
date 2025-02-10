package dev.alexguesser.ride.infra.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestRideDto(
        String passengerId,
        double fromLat,
        double fromLong,
        double toLat,
        double toLong
) {
}
