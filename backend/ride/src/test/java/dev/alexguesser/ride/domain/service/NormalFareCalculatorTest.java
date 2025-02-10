package dev.alexguesser.ride.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NormalFareCalculatorTest {

    @Test
    void calculateReturnsCorrectFareForPositiveDistance() {
        NormalFareCalculator calculator = new NormalFareCalculator();
        double fare = calculator.calculate(10.0);
        assertEquals(21.0, fare);
    }

    @Test
    void calculateReturnsZeroForZeroDistance() {
        NormalFareCalculator calculator = new NormalFareCalculator();
        double fare = calculator.calculate(0.0);
        assertEquals(0.0, fare);
    }

}