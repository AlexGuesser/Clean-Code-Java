package dev.alexguesser.ride.domain.service;

public class SpecialDayFareCalculator implements FareCalculator {

    @Override
    public float calculate(float distance) {
        return distance;
    }
}
