package com.example.endtermproject.controller;

import com.example.endtermproject.dto.BookingRequest;
import com.example.endtermproject.dto.BookingResponse;
import com.example.endtermproject.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }


    @GetMapping
    public List<BookingResponse> getAll(@RequestParam(required = false) Long hotelId) {
        return service.getAll(hotelId);
    }

    @GetMapping("/{id}")
    public BookingResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public BookingResponse create(@Valid @RequestBody BookingRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    public BookingResponse update(@PathVariable Long id, @Valid @RequestBody BookingRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    @PostMapping("/{id}/confirm")
    public BookingResponse confirm(@PathVariable Long id) {
        return service.confirm(id);
    }
}
