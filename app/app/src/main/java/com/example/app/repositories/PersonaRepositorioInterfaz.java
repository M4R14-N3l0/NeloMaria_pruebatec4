package com.example.app.repositories;

import com.example.app.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonaRepositorioInterfaz extends JpaRepository<Persona, Long> {
    List<Persona> findByName(String name_);

}
