package dev.alexguesser.ride.domain.service;

public class OvernightFareCalculator implements FareCalculator {

    @Override
    public double calculate(double distance) {
        return distance * 3.9;
    }

}
