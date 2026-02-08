package com.example.endtermproject.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("HOTEL")
public class HotelBooking extends Booking {

    private boolean breakfastIncluded; // пример поля подтипа

    public boolean isBreakfastIncluded() { return breakfastIncluded; }
    public void setBreakfastIncluded(boolean breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    @Override
    public BookingType getType() {
        return BookingType.HOTEL;
    }
}
