package dev.alexguesser.ride.infra.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dev.alexguesser.ride.application.usecase.output.GetRideOutput;

@JsonIgnoreProperties
public record GetRideDto(
        String rideId,
        String passengerId,
        String driverId,
        double fare,
        double distance
) {

    public static GetRideDto fromOutput(GetRideOutput output) {
        return new GetRideDto(
                output.rideId().toString(),
                output.passengerId().toString(),
                output.driverId().toString(),
                output.fare(),
                output.distance()
        );
    }

}
