package dev.alexguesser.ride.domain.service;

public class OvernightFareCalculator implements FareCalculator {

    @Override
    public float calculate(float distance) {
        return distance * 3.9f;
    }

}
