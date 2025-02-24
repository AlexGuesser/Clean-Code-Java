package dev.alexguesser.ride.infra.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.alexguesser.ride.application.usecase.AcceptRide;
import dev.alexguesser.ride.application.usecase.GetRide;
import dev.alexguesser.ride.application.usecase.RequestRide;
import dev.alexguesser.ride.application.usecase.input.RequestRideInput;
import dev.alexguesser.ride.domain.service.ChangeStatusService;
import dev.alexguesser.ride.infra.controller.dto.GetRideDto;
import dev.alexguesser.ride.infra.controller.dto.NewStatusDto;
import dev.alexguesser.ride.infra.controller.dto.RequestRideDto;
import dev.alexguesser.ride.infra.controller.dto.RideDto;

@RestController
@RequestMapping("/ride")
public class RideController {

    @Autowired
    private GetRide getRide;

    @Autowired
    private RequestRide requestRide;

    @Autowired
    private AcceptRide acceptRide;

    @Autowired
    private ChangeStatusService changeStatusService;


    @GetMapping("/{rideId}")
    public ResponseEntity<GetRideDto> getRideById(@PathVariable("rideId") UUID rideId) {
        return ResponseEntity.ok(
                GetRideDto.fromOutput(
                        getRide.execute(rideId)
                ));
    }

    @PostMapping
    public ResponseEntity<RideDto> requestRide(@RequestBody RequestRideDto dto) {
        return ResponseEntity.ok(
                RideDto.fromOutput(
                        requestRide.execute(
                                RequestRideInput.fromDto(dto)
                        )
                )
        );
    }

    @PatchMapping("/{rideId}/newStatus")
    public ResponseEntity<?> newStatus(@PathVariable("rideId") UUID rideId, @RequestBody NewStatusDto dto) {
        return ResponseEntity.ok(
                changeStatusService.changeStatus(dto.newStatus(), rideId, dto.driverId())
        );
    }

}
