package com.example.endtermproject.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "booking_kind")
public abstract class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    @Column(nullable = false)
    private String guestName;

    @Column(nullable = false)
    private String guestEmail;

    @Column(nullable = false)
    private LocalDate checkIn;

    @Column(nullable = false)
    private LocalDate checkOut;

    @Column(nullable = false)
    private int rooms;

    @Column(nullable = false)
    private double totalPrice;

    private String specialRequests; // optional

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    protected Booking() {}

    public Long getId() { return id; }
    public BookingStatus getStatus() { return status; }
    public String getGuestName() { return guestName; }
    public String getGuestEmail() { return guestEmail; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public int getRooms() { return rooms; }
    public double getTotalPrice() { return totalPrice; }
    public String getSpecialRequests() { return specialRequests; }
    public Hotel getHotel() { return hotel; }

    public void setId(Long id) { this.id = id; }
    public void setStatus(BookingStatus status) { this.status = status; }
    public void setGuestName(String guestName) { this.guestName = guestName; }
    public void setGuestEmail(String guestEmail) { this.guestEmail = guestEmail; }
    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }
    public void setRooms(int rooms) { this.rooms = rooms; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void setSpecialRequests(String specialRequests) { this.specialRequests = specialRequests; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }


    public abstract BookingType getType();
}
