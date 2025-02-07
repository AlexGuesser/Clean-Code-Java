package dev.alexguesser.ride.infra.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.alexguesser.ride.application.usecase.GetRide;
import dev.alexguesser.ride.application.usecase.RequestRide;
import dev.alexguesser.ride.application.usecase.input.RequestRideInput;
import dev.alexguesser.ride.application.usecase.output.GetRideOutput;
import dev.alexguesser.ride.infra.controller.dto.RequestRideDto;
import dev.alexguesser.ride.infra.controller.dto.RideDto;

@RestController
@RequestMapping("/ride")
public class RideController {

    @Autowired
    private GetRide getRide;

    @Autowired
    private RequestRide requestRide;


    @GetMapping("/{rideId}")
    public ResponseEntity<GetRideOutput> getRideById(@PathVariable("rideId") UUID rideId) {
        return ResponseEntity.ok(
                getRide.execute(rideId)
        );
    }

    @PostMapping
    public ResponseEntity<RideDto> requestRide(@RequestBody RequestRideDto dto) {
        return ResponseEntity.ok(
                RideDto.fromOutput(
                        requestRide.execute(
                                RequestRideInput.toInput(dto)
                        )
                )
        );
    }

}
