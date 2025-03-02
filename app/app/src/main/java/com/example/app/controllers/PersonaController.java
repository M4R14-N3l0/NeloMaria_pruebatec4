package com.example.app.controllers;

import com.example.app.dtos.PersonaDTO;
import com.example.app.services.PersonaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    private final PersonaServicio personaService;

    public PersonaController(PersonaServicio personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public List<PersonaDTO> getAllPersonas() {
        return personaService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> getPersonaById(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.obtenerPersonaPorId(id));
    }

    @PostMapping ("/new")
    public ResponseEntity<PersonaDTO> createPersona(@RequestBody PersonaDTO personaDTO) {
        return ResponseEntity.ok(personaService.crearPersona(personaDTO));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PersonaDTO> updatePersona(@PathVariable Long id, @RequestBody PersonaDTO personaDTO) {
        return ResponseEntity.ok(personaService.actualizarPersona(id, personaDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        personaService.eliminarPersona(id);
        return ResponseEntity.noContent().build();
    }
}
