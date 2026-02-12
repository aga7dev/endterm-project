package com.example.endtermproject.service;

import com.example.endtermproject.cache.InMemoryCache;
import com.example.endtermproject.dto.HotelRequest;
import com.example.endtermproject.dto.HotelResponse;
import com.example.endtermproject.exception.NotFoundException;
import com.example.endtermproject.model.Hotel;
import com.example.endtermproject.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private final HotelRepository repo;


    private final InMemoryCache cache = InMemoryCache.getInstance();

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
        String key = "hotel_" + id;


        Optional<Object> cached = cache.get(key);
        if (cached.isPresent()) {
            return (HotelResponse) cached.get();
        }


        Hotel hotel = getHotelEntity(id);
        HotelResponse response = toResponse(hotel);


        cache.put(key, response);

        return response;
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


        cache.evict("hotel_" + id);

        return toResponse(saved);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Hotel not found: " + id);
        }

        repo.deleteById(id);


        cache.evict("hotel_" + id);
    }

    public Hotel getHotelEntity(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Hotel not found: " + id));
    }

    private HotelResponse toResponse(Hotel h) {
        return new HotelResponse(h.getId(), h.getName(), h.getCity(), h.getStars());
    }
}
