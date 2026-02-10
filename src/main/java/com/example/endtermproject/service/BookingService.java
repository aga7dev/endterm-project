package com.example.endtermproject.service;

import com.example.endtermproject.dto.BookingRequest;
import com.example.endtermproject.dto.BookingResponse;
import com.example.endtermproject.exception.BadRequestException;
import com.example.endtermproject.exception.NotFoundException;
import com.example.endtermproject.model.*;
import com.example.endtermproject.patterns.builder.BookingBuilder;
import com.example.endtermproject.patterns.factory.BookingFactory;
import com.example.endtermproject.patterns.singleton.LoggingService;
import com.example.endtermproject.repository.BookingRepository;
import com.example.endtermproject.utils.PriceCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository repo;
    private final HotelService hotelService;
    private final LoggingService log = LoggingService.getInstance(); // Singleton usage

    public BookingService(BookingRepository repo, HotelService hotelService) {
        this.repo = repo;
        this.hotelService = hotelService;
    }

    public List<BookingResponse> getAll(Long hotelId) {
        List<Booking> list = (hotelId == null) ? repo.findAll() : repo.findByHotel_Id(hotelId);
        return list.stream().map(this::toResponse).toList();
    }

    public BookingResponse getById(Long id) {
        Booking b = repo.findById(id).orElseThrow(() -> new NotFoundException("Booking not found: " + id));
        return toResponse(b);
    }

    public BookingResponse create(BookingRequest req) {
        validateDates(req);

        Hotel hotel = hotelService.getHotelEntity(req.getHotelId());

        Booking booking = BookingFactory.createBooking(req);

        double price = PriceCalculator.calculate(req.getCheckIn(), req.getCheckOut(), req.getRooms(), 50.0);


        booking = new BookingBuilder(booking)
                .status(BookingStatus.NEW)
                .hotel(hotel)
                .guest(req.getGuestName(), req.getGuestEmail())
                .dates(req.getCheckIn(), req.getCheckOut())
                .rooms(req.getRooms())
                .totalPrice(price)
                .specialRequests(req.getSpecialRequests())
                .build();

        booking = repo.save(booking);
        log.info("Created booking id=" + booking.getId() + " type=" + booking.getType());

        return toResponse(booking);
    }

    public BookingResponse update(Long id, BookingRequest req) {
        validateDates(req);

        Booking existing = repo.findById(id).orElseThrow(() -> new NotFoundException("Booking not found: " + id));
        Hotel hotel = hotelService.getHotelEntity(req.getHotelId());


        if (req.getType() != null && req.getType() != existing.getType()) {
            throw new BadRequestException("Cannot change booking type after creation");
        }

        existing.setHotel(hotel);
        existing.setGuestName(req.getGuestName());
        existing.setGuestEmail(req.getGuestEmail());
        existing.setCheckIn(req.getCheckIn());
        existing.setCheckOut(req.getCheckOut());
        existing.setRooms(req.getRooms());
        existing.setSpecialRequests(req.getSpecialRequests());

        double price = PriceCalculator.calculate(req.getCheckIn(), req.getCheckOut(), req.getRooms(), 50.0);
        existing.setTotalPrice(price);


        if (existing instanceof HotelBooking hb && req.getBreakfastIncluded() != null) {
            hb.setBreakfastIncluded(req.getBreakfastIncluded());
        }
        if (existing instanceof ApartmentBooking ab && req.getHasKitchen() != null) {
            ab.setHasKitchen(req.getHasKitchen());
        }

        existing = repo.save(existing);
        log.info("Updated booking id=" + existing.getId());

        return toResponse(existing);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Booking not found: " + id);
        repo.deleteById(id);
        log.info("Deleted booking id=" + id);
    }

    public BookingResponse confirm(Long id) {
        Booking b = repo.findById(id).orElseThrow(() -> new NotFoundException("Booking not found: " + id));
        b.setStatus(BookingStatus.CONFIRMED);
        b = repo.save(b);
        return toResponse(b);
    }

    private void validateDates(BookingRequest req) {
        if (req.getCheckIn() == null || req.getCheckOut() == null) {
            throw new BadRequestException("checkIn/checkOut are required");
        }
        if (!req.getCheckOut().isAfter(req.getCheckIn())) {
            throw new BadRequestException("checkOut must be after checkIn");
        }
    }

    private BookingResponse toResponse(Booking b) {
        Boolean breakfast = null;
        Boolean kitchen = null;

        if (b instanceof HotelBooking hb) breakfast = hb.isBreakfastIncluded();
        if (b instanceof ApartmentBooking ab) kitchen = ab.isHasKitchen();

        return new BookingResponse(
                b.getId(),
                b.getType(),
                b.getStatus(),
                b.getHotel().getId(),
                b.getGuestName(),
                b.getGuestEmail(),
                b.getCheckIn(),
                b.getCheckOut(),
                b.getRooms(),
                b.getTotalPrice(),
                b.getSpecialRequests(),
                breakfast,
                kitchen
        );
    }
}
