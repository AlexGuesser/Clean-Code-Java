package dev.alexguesser.ride.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class FareCalculatorFactoryTest {

    @Test
    void getFareCalculatorReturnsSpecialDayFareCalculatorOnFirstDayOfMonth() {
        LocalDateTime date = LocalDateTime.of(2023, 10, 1, 12, 0);
        FareCalculator fareCalculator = FareCalculatorFactory.getFareCalculator(date);
        assertInstanceOf(SpecialDayFareCalculator.class, fareCalculator);
    }

    @Test
    void getFareCalculatorReturnsOvernightFareCalculatorAtNight() {
        LocalDateTime date = LocalDateTime.of(2023, 10, 15, 23, 0);
        FareCalculator fareCalculator = FareCalculatorFactory.getFareCalculator(date);
        assertInstanceOf(OvernightFareCalculator.class, fareCalculator);
    }

    @Test
    void getFareCalculatorReturnsOvernightFareCalculatorEarlyMorning() {
        LocalDateTime date = LocalDateTime.of(2023, 10, 15, 5, 0);
        FareCalculator fareCalculator = FareCalculatorFactory.getFareCalculator(date);
        assertInstanceOf(OvernightFareCalculator.class, fareCalculator);
    }

    @Test
    void getFareCalculatorReturnsNormalFareCalculatorDuringDay() {
        LocalDateTime date = LocalDateTime.of(2023, 10, 15, 12, 0);
        FareCalculator fareCalculator = FareCalculatorFactory.getFareCalculator(date);
        assertInstanceOf(NormalFareCalculator.class, fareCalculator);
    }

    @Test
    void getFareCalculatorReturnsNormalFareCalculatorOnNonFirstDayOfMonth() {
        LocalDateTime date = LocalDateTime.of(2023, 10, 2, 12, 0);
        FareCalculator fareCalculator = FareCalculatorFactory.getFareCalculator(date);
        assertInstanceOf(NormalFareCalculator.class, fareCalculator);
    }

}