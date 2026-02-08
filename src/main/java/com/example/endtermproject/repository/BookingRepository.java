package com.example.endtermproject.repository;

import com.example.endtermproject.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByHotel_Id(Long hotelId);
}
