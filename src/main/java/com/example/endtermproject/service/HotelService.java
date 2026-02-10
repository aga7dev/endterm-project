package com.example.endtermproject.service;

import com.example.endtermproject.dto.HotelRequest;
import com.example.endtermproject.dto.HotelResponse;
import com.example.endtermproject.exception.NotFoundException;
import com.example.endtermproject.model.Hotel;
import com.example.endtermproject.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private final HotelRepository repo;

    public HotelService(HotelRepository repo) {
        this.repo = repo;
    }

    public List<HotelResponse> getAll() {
        return repo.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public HotelResponse getById(Long id) {
        Hotel hotel = getHotelEntity(id);
        return toResponse(hotel);
    }

    public HotelResponse create(HotelRequest req) {
        Hotel hotel = new Hotel(req.getName(), req.getCity(), req.getStars());
        Hotel saved = repo.save(hotel);
        return toResponse(saved);
    }

    public HotelResponse update(Long id, HotelRequest req) {
        Hotel hotel = getHotelEntity(id);
        hotel.setName(req.getName());
        hotel.setCity(req.getCity());
        hotel.setStars(req.getStars());
        Hotel saved = repo.save(hotel);
        return toResponse(saved);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Hotel not found: " + id);
        }
        repo.deleteById(id);
    }


    public Hotel getHotelEntity(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Hotel not found: " + id));
    }

    private HotelResponse toResponse(Hotel h) {
        return new HotelResponse(h.getId(), h.getName(), h.getCity(), h.getStars());
    }
}
