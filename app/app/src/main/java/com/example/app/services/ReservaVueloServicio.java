package com.example.app.services;

import com.example.app.dtos.PersonaDTO;
import com.example.app.dtos.ReservaVueloDTO;
import com.example.app.dtos.VueloDTO;
import com.example.app.entities.Persona;
import com.example.app.entities.ReservaVuelo;
import com.example.app.entities.Vuelo;
import com.example.app.repositories.PersonaRepositorioInterfaz;
import com.example.app.repositories.ReservaVueloRepositorio;
import com.example.app.repositories.VueloRepositorioInterfaz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaVueloServicio implements ReservaVueloInterfaz {

    private static final Logger logger = LoggerFactory.getLogger(ReservaVueloServicio.class);

    private final ReservaVueloRepositorio reservaVueloRepositorio;
    private final VueloRepositorioInterfaz vueloRepositorio;
    private final PersonaRepositorioInterfaz personaRepositorio;

    public ReservaVueloServicio(ReservaVueloRepositorio reservaVueloRepositorio, VueloRepositorioInterfaz vueloRepositorio, PersonaRepositorioInterfaz personaRepositorio) {
        this.reservaVueloRepositorio = reservaVueloRepositorio;
        this.vueloRepositorio = vueloRepositorio;
        this.personaRepositorio = personaRepositorio;
    }


    @Override
    public List<ReservaVueloDTO> obtenerTodasLasReservas() {
        return reservaVueloRepositorio.findAll()
                .stream()
                .map(this::convertirHaciaDTO)
                .collect(Collectors.toList());
    }


    @Override
    public ReservaVueloDTO obtenerReservaPorId(Long id) {
        ReservaVuelo reserva = reservaVueloRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));

        return convertirHaciaDTO(reserva);
    }


    @Override
    public ReservaVueloDTO crearReservaVuelo(ReservaVueloDTO reservaVueloDTO) {
        logger.info("Creando nueva reserva de vuelo...");


        Vuelo vuelo = vueloRepositorio.findById(reservaVueloDTO.getVueloId())
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado con ID: " + reservaVueloDTO.getVueloId()));


        Persona persona = personaRepositorio.findById(reservaVueloDTO.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + reservaVueloDTO.getPersonaId()));


        ReservaVuelo nuevaReserva = new ReservaVuelo();
        nuevaReserva.setFechaReserva(reservaVueloDTO.getFechaReserva());
        nuevaReserva.setPersona(persona);
        nuevaReserva.setVuelo(vuelo);


        ReservaVuelo reservaGuardada = reservaVueloRepositorio.save(nuevaReserva);
        logger.info("Reserva creada con éxito, ID: {}", reservaGuardada.getId());

        return convertirHaciaDTO(reservaGuardada);
    }


    @Override
    public ReservaVueloDTO actualizarReservaVuelo(Long id, ReservaVueloDTO reservaVueloDTO) {
        ReservaVuelo reserva = reservaVueloRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));

        // Si hay un nuevo vuelo, lo asignamos
        if (reservaVueloDTO.getVueloId() != null) {
            Vuelo nuevoVuelo = vueloRepositorio.findById(reservaVueloDTO.getVueloId())
                    .orElseThrow(() -> new RuntimeException("Vuelo no encontrado con ID: " + reservaVueloDTO.getVueloId()));
            reserva.setVuelo(nuevoVuelo);
        }

        // Si hay una nueva persona, la asignamos
        if (reservaVueloDTO.getPersonaId() != null) {
            Persona nuevaPersona = personaRepositorio.findById(reservaVueloDTO.getPersonaId())
                    .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + reservaVueloDTO.getPersonaId()));
            reserva.setPersona(nuevaPersona);
        }

        // Guardar cambios
        ReservaVuelo reservaActualizada = reservaVueloRepositorio.save(reserva);
        return convertirHaciaDTO(reservaActualizada);
    }


    @Override
    public void eliminarReservaVuelo(Long id) {
        if (!reservaVueloRepositorio.existsById(id)) {
            throw new RuntimeException("Reserva no encontrada con ID: " + id);
        }
        reservaVueloRepositorio.deleteById(id);
        logger.info("Reserva con ID {} eliminada con éxito.", id);
    }


    private ReservaVueloDTO convertirHaciaDTO(ReservaVuelo reservaVuelo) {
        return new ReservaVueloDTO(
                reservaVuelo.getId(),
                reservaVuelo.getFechaReserva(),
                reservaVuelo.getPersona().getId(),
                reservaVuelo.getVuelo().getId()
        );
    }
}
