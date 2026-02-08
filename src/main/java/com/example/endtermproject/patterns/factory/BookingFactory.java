package com.example.endtermproject.patterns.factory;

import com.example.endtermproject.dto.BookingRequest;
import com.example.endtermproject.model.*;

public class BookingFactory {

    private BookingFactory() {}

    public static Booking createBooking(BookingRequest req) {
        if (req.getType() == null) {
            throw new IllegalArgumentException("Booking type is required");
        }

        return switch (req.getType()) {
            case HOTEL -> {
                HotelBooking b = new HotelBooking();
                b.setBreakfastIncluded(Boolean.TRUE.equals(req.getBreakfastIncluded()));
                yield b;
            }
            case APARTMENT -> {
                ApartmentBooking b = new ApartmentBooking();
                b.setHasKitchen(Boolean.TRUE.equals(req.getHasKitchen()));
                yield b;
            }
        };
    }
}
