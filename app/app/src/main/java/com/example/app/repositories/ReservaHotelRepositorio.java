package com.example.app.repositories;

import com.example.app.entities.ReservaHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaHotelRepositorio extends JpaRepository<ReservaHotel, Long> {
}
