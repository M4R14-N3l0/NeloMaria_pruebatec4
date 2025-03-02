package com.example.app.services;

import com.example.app.dtos.PersonaDTO;
import com.example.app.entities.Persona;
import java.util.List;

public interface PersonaServicioInterfaz {

    List<PersonaDTO> buscarPersonasPorNombre(String nombre);

    List<PersonaDTO> buscarTodos();

    PersonaDTO obtenerPersonaPorId(Long id);

    PersonaDTO crearPersona(PersonaDTO personaDTO);

    PersonaDTO actualizarPersona(Long id, PersonaDTO personaDTO);

    void eliminarPersona(Long id);

    //conversores
    PersonaDTO convertirHaciaDTO(Persona persona);

    Persona convertirHaciaEntidad(PersonaDTO personaDTO);
}
