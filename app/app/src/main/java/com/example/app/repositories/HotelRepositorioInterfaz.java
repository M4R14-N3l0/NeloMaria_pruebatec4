package com.example.app.repositories;

import com.example.app.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelRepositorioInterfaz extends JpaRepository<Hotel, Long> {
    List<Hotel> findByPlace(String place);

}
