package dev.alexguesser.ride.domain.service;

import java.time.ZonedDateTime;

public class FareCalculatorFactory {

    public FareCalculator getFareCalculator(ZonedDateTime date) {
        if (date.getDayOfMonth() == 1) {
            return new SpecialDayFareCalculator();
        }
        if (date.getHour() >= 22 || date.getHour() < 6) {
            return new OvernightFareCalculator();
        }
        return new NormalFareCalculator();
    }

}
