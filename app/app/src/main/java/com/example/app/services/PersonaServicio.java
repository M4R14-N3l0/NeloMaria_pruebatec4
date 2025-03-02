package com.example.app.services;

import com.example.app.dtos.PersonaDTO;
import com.example.app.entities.Persona;
import com.example.app.repositories.PersonaRepositorioInterfaz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaServicio implements PersonaServicioInterfaz {

    private static final Logger logger = LoggerFactory.getLogger(PersonaServicio.class);
    private final PersonaRepositorioInterfaz repository;

    public PersonaServicio(PersonaRepositorioInterfaz repository) {
        this.repository = repository;
    }


    @Override
    public List<PersonaDTO> buscarTodos() {
        return repository.findAll().stream()
                .map(this::convertirHaciaDTO)
                .collect(Collectors.toList());
    }


    @Override
    public PersonaDTO obtenerPersonaPorId(Long id) {
        return repository.findById(id)
                .map(this::convertirHaciaDTO)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + id));
    }


    @Override
    public List<PersonaDTO> buscarPersonasPorNombre(String nombre) {
        List<Persona> listado = nombre == null || nombre.isBlank()
                ? repository.findAll()
                : repository.findByName(nombre);

        return listado.stream()
                .map(this::convertirHaciaDTO)
                .collect(Collectors.toList());
    }


    @Override
    public PersonaDTO crearPersona(PersonaDTO personaDTO) {
        logger.info("Creando persona: {} {}", personaDTO.getName(), personaDTO.getLast_name());
        Persona persona = convertirHaciaEntidad(personaDTO);
        Persona personaGuardada = repository.save(persona);
        logger.info("Persona guardada con ID: {}", personaGuardada.getId());
        return convertirHaciaDTO(personaGuardada);
    }


    @Override
    public PersonaDTO actualizarPersona(Long id, PersonaDTO personaDTO) {
        return repository.findById(id).map(persona -> {
            if (personaDTO.getName() != null) persona.setName(personaDTO.getName());
            if (personaDTO.getLast_name() != null) persona.setLastName(personaDTO.getLast_name());

            Persona personaActualizada = repository.save(persona);
            return convertirHaciaDTO(personaActualizada);
        }).orElseThrow(() -> new RuntimeException("Persona no encontrada con id: " + id));
    }


    @Override
    public void eliminarPersona(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Persona no encontrada con id: " + id);
        }
        repository.deleteById(id);
    }


    @Override
    public PersonaDTO convertirHaciaDTO(Persona persona) {
        return new PersonaDTO(
                persona.getId(),
                persona.getName(),
                persona.getLastName(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }


    @Override
    public Persona convertirHaciaEntidad(PersonaDTO personaDTO) {
        return new Persona(
                personaDTO.getId(),
                personaDTO.getName(),
                personaDTO.getLast_name(),
                null, null
        );
    }
}
