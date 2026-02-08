package com.example.endtermproject.dto;

import com.example.endtermproject.model.BookingType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class BookingRequest {

    @NotNull
    private BookingType type; // HOTEL или APARTMENT

    @NotNull
    private Long hotelId;

    @NotBlank
    private String guestName;

    @Email @NotBlank
    private String guestEmail;

    @NotNull
    private LocalDate checkIn;

    @NotNull
    private LocalDate checkOut;

    @Min(1)
    private int rooms;


    private String specialRequests;


    private Boolean breakfastIncluded; // for HOTEL
    private Boolean hasKitchen;        // for APARTMENT

    public BookingType getType() { return type; }
    public Long getHotelId() { return hotelId; }
    public String getGuestName() { return guestName; }
    public String getGuestEmail() { return guestEmail; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public int getRooms() { return rooms; }
    public String getSpecialRequests() { return specialRequests; }
    public Boolean getBreakfastIncluded() { return breakfastIncluded; }
    public Boolean getHasKitchen() { return hasKitchen; }

    public void setType(BookingType type) { this.type = type; }
    public void setHotelId(Long hotelId) { this.hotelId = hotelId; }
    public void setGuestName(String guestName) { this.guestName = guestName; }
    public void setGuestEmail(String guestEmail) { this.guestEmail = guestEmail; }
    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }
    public void setRooms(int rooms) { this.rooms = rooms; }
    public void setSpecialRequests(String specialRequests) { this.specialRequests = specialRequests; }
    public void setBreakfastIncluded(Boolean breakfastIncluded) { this.breakfastIncluded = breakfastIncluded; }
    public void setHasKitchen(Boolean hasKitchen) { this.hasKitchen = hasKitchen; }
}
