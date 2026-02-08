package com.example.endtermproject.patterns.builder;

import com.example.endtermproject.model.Booking;
import com.example.endtermproject.model.BookingStatus;
import com.example.endtermproject.model.Hotel;

import java.time.LocalDate;

public class BookingBuilder {

    private final Booking booking;

    public BookingBuilder(Booking booking) {
        this.booking = booking;
    }

    public BookingBuilder status(BookingStatus status) {
        booking.setStatus(status);
        return this;
    }

    public BookingBuilder hotel(Hotel hotel) {
        booking.setHotel(hotel);
        return this;
    }

    public BookingBuilder guest(String name, String email) {
        booking.setGuestName(name);
        booking.setGuestEmail(email);
        return this;
    }

    public BookingBuilder dates(LocalDate checkIn, LocalDate checkOut) {
        booking.setCheckIn(checkIn);
        booking.setCheckOut(checkOut);
        return this;
    }

    public BookingBuilder rooms(int rooms) {
        booking.setRooms(rooms);
        return this;
    }

    public BookingBuilder totalPrice(double totalPrice) {
        booking.setTotalPrice(totalPrice);
        return this;
    }

    public BookingBuilder specialRequests(String specialRequests) {
        booking.setSpecialRequests(specialRequests);
        return this;
    }

    public Booking build() {
        return booking;
    }
}
