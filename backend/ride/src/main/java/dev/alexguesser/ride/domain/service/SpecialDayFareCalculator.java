package dev.alexguesser.ride.domain.service;

public class SpecialDayFareCalculator implements FareCalculator {

    @Override
    public double calculate(double distance) {
        return distance;
    }
}
