package com.example.endtermproject.utils;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

public class PriceCalculator {

    private PriceCalculator() {}

    public static double calculate(LocalDate checkIn, LocalDate checkOut, int rooms, double basePerNight) {
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (nights <= 0) return 0;
        return nights * rooms * basePerNight;
    }
}
