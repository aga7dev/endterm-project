package com.example.endtermproject.controller;

import com.example.endtermproject.dto.HotelRequest;
import com.example.endtermproject.dto.HotelResponse;
import com.example.endtermproject.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @GetMapping
    public List<HotelResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public HotelResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public HotelResponse create(@Valid @RequestBody HotelRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    public HotelResponse update(@PathVariable Long id, @Valid @RequestBody HotelRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
