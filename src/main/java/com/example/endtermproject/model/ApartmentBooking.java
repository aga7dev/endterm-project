package com.example.endtermproject.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("APARTMENT")
public class ApartmentBooking extends Booking {

    private boolean hasKitchen;

    public boolean isHasKitchen() { return hasKitchen; }
    public void setHasKitchen(boolean hasKitchen) { this.hasKitchen = hasKitchen; }

    @Override
    public BookingType getType() {
        return BookingType.APARTMENT;
    }
}
