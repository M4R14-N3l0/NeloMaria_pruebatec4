package com.example.app.services;

import com.example.app.dtos.HotelDTO;
import com.example.app.dtos.VueloDTO;
import com.example.app.entities.Hotel;
import com.example.app.entities.Vuelo;
import com.example.app.repositories.VueloRepositorioInterfaz;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VueloServicio {

    private static final Logger logger = LoggerFactory.getLogger(VueloServicio.class);
    private final VueloRepositorioInterfaz vueloRepository;

    public VueloServicio(VueloRepositorioInterfaz vueloRepository) {
        this.vueloRepository = vueloRepository;
    }


    private VueloDTO convertToDTO(Vuelo vuelo) {
        return new VueloDTO(
                vuelo.getId(),
                vuelo.getFlightNumber(),
                vuelo.getName_(),
                vuelo.getOrigin(),
                vuelo.getDestination(),
                vuelo.getSeatType(),
                vuelo.getFlightPrice(),
                vuelo.getDate(),
                null // Si necesitas agregar reservas, obtén los IDs aquí
        );
    }


    public List<VueloDTO> getAllVuelos() {
        return vueloRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public VueloDTO getVueloById(Long id) {
        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado con ID: " + id));
        return convertToDTO(vuelo);
    }


    public VueloDTO saveVuelo(@NotNull VueloDTO vueloDTO) {
        // Crear entidad Vuelo a partir del DTO
        Vuelo vuelo = new Vuelo();
        vuelo.setFlightNumber(vueloDTO.getFlightNumber());
        vuelo.setName_(vueloDTO.getName());
        vuelo.setOrigin(vueloDTO.getOrigin());
        vuelo.setDestination(vueloDTO.getDestination());
        vuelo.setSeatType(vueloDTO.getSeatType());
        vuelo.setFlightPrice(vueloDTO.getFlightPrice());
        vuelo.setDate(vueloDTO.getDate_());

        // Guardar vuelo en la base de datos
        Vuelo savedVuelo = vueloRepository.save(vuelo);

        // Convertir la entidad guardada a DTO y devolverla
        return convertToDTO(savedVuelo);
    }


    public Optional<VueloDTO> updateVuelo(Long id, VueloDTO vueloDTO) {
        return vueloRepository.findById(id).map(vuelo -> {
            vuelo.setFlightNumber(vueloDTO.getFlightNumber());
            vuelo.setName_(vueloDTO.getName());
            vuelo.setOrigin(vueloDTO.getOrigin());
            vuelo.setDestination(vueloDTO.getDestination());
            vuelo.setSeatType(vueloDTO.getSeatType());
            vuelo.setFlightPrice(vueloDTO.getFlightPrice());
            vuelo.setDate(vueloDTO.getDate_());

            Vuelo updatedVuelo = vueloRepository.save(vuelo);
            return convertToDTO(updatedVuelo);
        });
    }

    public void deleteVuelo(Long id) {
        if (!vueloRepository.existsById(id)) {
            throw new RuntimeException("Vuelo no encontrado con ID: " + id);
        }
        vueloRepository.deleteById(id);
    }


    public List<VueloDTO> findByOriginAndDestination(String origin, String destination) {
        return vueloRepository.findByOriginAndDestination(origin, destination)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public List<VueloDTO> findByDate(LocalDate date) {
        return vueloRepository.findByDate(date)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
