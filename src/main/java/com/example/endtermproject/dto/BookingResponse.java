package com.example.endtermproject.dto;


import com.example.endtermproject.model.BookingStatus;
import com.example.endtermproject.model.BookingType;

import java.time.LocalDate;

public class BookingResponse {
    private Long id;
    private BookingType type;
    private BookingStatus status;
    private Long hotelId;

    private String guestName;
    private String guestEmail;

    private LocalDate checkIn;
    private LocalDate checkOut;

    private int rooms;
    private double totalPrice;

    private String specialRequests;


    private Boolean breakfastIncluded;
    private Boolean hasKitchen;

    public BookingResponse(Long id, BookingType type, BookingStatus status, Long hotelId,
                           String guestName, String guestEmail, LocalDate checkIn, LocalDate checkOut,
                           int rooms, double totalPrice, String specialRequests,
                           Boolean breakfastIncluded, Boolean hasKitchen) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.hotelId = hotelId;
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.rooms = rooms;
        this.totalPrice = totalPrice;
        this.specialRequests = specialRequests;
        this.breakfastIncluded = breakfastIncluded;
        this.hasKitchen = hasKitchen;
    }

    public Long getId() { return id; }
    public BookingType getType() { return type; }
    public BookingStatus getStatus() { return status; }
    public Long getHotelId() { return hotelId; }
    public String getGuestName() { return guestName; }
    public String getGuestEmail() { return guestEmail; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public int getRooms() { return rooms; }
    public double getTotalPrice() { return totalPrice; }
    public String getSpecialRequests() { return specialRequests; }
    public Boolean getBreakfastIncluded() { return breakfastIncluded; }
    public Boolean getHasKitchen() { return hasKitchen; }
}
