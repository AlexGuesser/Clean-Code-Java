package dev.alexguesser.ride.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OvernightFareCalculatorTest {

    @Test
    void calculateReturnsCorrectFareForPositiveDistance() {
        OvernightFareCalculator calculator = new OvernightFareCalculator();
        double fare = calculator.calculate(10.0);
        assertEquals(39.0, fare);
    }

    @Test
    void calculateReturnsZeroForZeroDistance() {
        OvernightFareCalculator calculator = new OvernightFareCalculator();
        double fare = calculator.calculate(0.0);
        assertEquals(0.0, fare);
    }

    @Test
    void calculateReturnsNegativeFareForNegativeDistance() {
        OvernightFareCalculator calculator = new OvernightFareCalculator();
        double fare = calculator.calculate(-5.0);
        assertEquals(-19.5, fare);
    }
}