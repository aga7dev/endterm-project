package com.example.endtermproject.repository;

import com.example.endtermproject.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> { }
