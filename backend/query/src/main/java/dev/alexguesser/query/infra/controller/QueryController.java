package dev.alexguesser.query.infra.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.alexguesser.query.application.usecase.GetRideComposition;

@RestController
@RequestMapping("/query/rides")
public class QueryController {

    @Autowired
    private GetRideComposition getRideComposition;


    @GetMapping("/composition/{rideId}")
    public ResponseEntity<?> getRideByComposition(@PathVariable("rideId") UUID rideId) {
        return ResponseEntity.ok(
                getRideComposition.execute(rideId)
        );
    }

}
