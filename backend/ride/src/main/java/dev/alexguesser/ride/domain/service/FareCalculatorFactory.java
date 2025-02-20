package dev.alexguesser.ride.domain.service;

import java.time.LocalDateTime;

public class FareCalculatorFactory {

    public static FareCalculator getFareCalculator(LocalDateTime date) {
        if (date.getDayOfMonth() == 1) {
            return new SpecialDayFareCalculator();
        }
        if (date.getHour() >= 22 || date.getHour() < 6) {
            return new OvernightFareCalculator();
        }
        return new NormalFareCalculator();
    }

}
