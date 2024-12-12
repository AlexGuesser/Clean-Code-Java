package dev.alexguesser.ride.infra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ride")
public class RideController {


    @GetMapping("/{rideId}")
    public ResponseEntity<?> getRideById() {
        return ResponseEntity.ok().build();
    }

}
