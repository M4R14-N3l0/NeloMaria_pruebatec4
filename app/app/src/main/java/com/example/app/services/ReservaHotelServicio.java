package com.example.app.services;

import com.example.app.dtos.ReservaHotelDTO;
import com.example.app.entities.Hotel;
import com.example.app.entities.ReservaHotel;
import com.example.app.repositories.HotelRepositorioInterfaz;
import com.example.app.repositories.ReservaHotelRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaHotelServicio {

    private static final Logger logger = LoggerFactory.getLogger(ReservaHotelServicio.class);
    private final ReservaHotelRepositorio reservaHotelRepository;
    private final HotelRepositorioInterfaz hotelRepository;

    public ReservaHotelServicio(ReservaHotelRepositorio reservaHotelRepository, HotelRepositorioInterfaz hotelRepository) {
        this.reservaHotelRepository = reservaHotelRepository;
        this.hotelRepository = hotelRepository;
    }


    private ReservaHotelDTO convertirAReservaHotelDTO(ReservaHotel reserva) {
        return new ReservaHotelDTO(
                reserva.getId(),
                reserva.getCheckInDate(),
                reserva.getCheckOutDate(),
                reserva.getPersona().getId(),
                reserva.getHotel().getHotelCode()
        );
    }


    public ReservaHotelDTO crearReservaHotel(ReservaHotelDTO reservaDTO) {
        logger.info("Intentando crear una reserva para el hotel ID: {}", reservaDTO.getHotelId());


        Hotel hotel = hotelRepository.findById(reservaDTO.getHotelId())
                .orElseThrow(() -> new IllegalArgumentException("Hotel no encontrado con ID: " + reservaDTO.getHotelId()));


        if (hotel.getAvailabilityDateFrom().isAfter(reservaDTO.getCheckInDate()) ||
                hotel.getAvailabilityDateTo().isBefore(reservaDTO.getCheckOutDate())) {
            throw new IllegalArgumentException("No hay disponibilidad en el hotel para esas fechas.");
        }


        ReservaHotel reserva = new ReservaHotel();
        reserva.setCheckInDate(reservaDTO.getCheckInDate());
        reserva.setCheckOutDate(reservaDTO.getCheckOutDate());
        reserva.setHotel(hotel);


        ReservaHotel reservaGuardada = reservaHotelRepository.save(reserva);
        logger.info("Reserva creada con éxito, ID: {}", reservaGuardada.getId());

        return convertirAReservaHotelDTO(reservaGuardada);
    }


    public List<ReservaHotelDTO> getAllReservas() {
        return reservaHotelRepository.findAll()
                .stream()
                .map(this::convertirAReservaHotelDTO)
                .collect(Collectors.toList());
    }


    public ReservaHotelDTO getReservaById(Long id) {
        ReservaHotel reserva = reservaHotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
        return convertirAReservaHotelDTO(reserva);
    }



    public void cancelReserva(Long id) {
        if (!reservaHotelRepository.existsById(id)) {
            throw new RuntimeException("Reserva no encontrada con ID: " + id);
        }
        reservaHotelRepository.deleteById(id);
        logger.info("Reserva con ID: {} eliminada exitosamente", id);
    }
}
