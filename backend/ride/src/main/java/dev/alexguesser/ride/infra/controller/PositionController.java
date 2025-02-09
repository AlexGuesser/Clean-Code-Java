package dev.alexguesser.ride.infra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.alexguesser.ride.application.usecase.UpdatePosition;
import dev.alexguesser.ride.application.usecase.input.UpdatePositionInput;
import dev.alexguesser.ride.infra.controller.dto.UpdatePositionDto;

@RestController
@RequestMapping("/ride/position")
public class PositionController {


    @Autowired
    private UpdatePosition updatePosition;

    @PutMapping
    public ResponseEntity<?> updatePosition(@RequestBody UpdatePositionDto dto) {
        updatePosition.execute(UpdatePositionInput.fromDto(dto));
        return ResponseEntity.ok(
                String.format("RideId: %s position updated to %d and %d", dto.rideId(), dto.latitude(), dto.longitude())
        );
    }
}
