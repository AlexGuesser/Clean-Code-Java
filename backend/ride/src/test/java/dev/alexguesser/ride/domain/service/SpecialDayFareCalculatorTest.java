package dev.alexguesser.ride.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SpecialDayFareCalculatorTest {

    @Test
    void calculateReturnsCorrectFareForPositiveDistance() {
        SpecialDayFareCalculator calculator = new SpecialDayFareCalculator();
        double fare = calculator.calculate(10.0);
        assertEquals(10.0, fare);
    }

    @Test
    void calculateReturnsZeroForZeroDistance() {
        SpecialDayFareCalculator calculator = new SpecialDayFareCalculator();
        double fare = calculator.calculate(0.0);
        assertEquals(0.0, fare);
    }

    @Test
    void calculateReturnsNegativeFareForNegativeDistance() {
        SpecialDayFareCalculator calculator = new SpecialDayFareCalculator();
        double fare = calculator.calculate(-5.0);
        assertEquals(-5.0, fare);
    }
}