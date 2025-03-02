package com.example.app.repositories;

import com.example.app.entities.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VueloRepositorioInterfaz extends JpaRepository<Vuelo, Long> {
    List<Vuelo> findByOriginAndDestination(String origin, String destination);
    List<Vuelo> findByDate(LocalDate date);
}

