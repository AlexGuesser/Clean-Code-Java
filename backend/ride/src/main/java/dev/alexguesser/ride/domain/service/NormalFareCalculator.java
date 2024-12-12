package dev.alexguesser.ride.domain.service;

public class NormalFareCalculator implements FareCalculator {

    @Override
    public float calculate(float distance) {
        return distance * 2.1f;
    }


}
