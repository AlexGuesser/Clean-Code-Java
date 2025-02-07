package dev.alexguesser.ride.infra.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dev.alexguesser.ride.application.usecase.output.RequestRideOutput;

@JsonIgnoreProperties
public record RideDto(
        String rideUuid
) {
    public static RideDto fromOutput(RequestRideOutput output) {
        return new RideDto(
                output.rideId().toString()
        );
    }
}
